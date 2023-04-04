package com.paolorusso.FinalProject.PrenotazioneCampiOnline.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.CampoDaGioco;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Prenotazione;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories.PrenotazioneRepository;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.services.CampoDaGiocoService;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.services.PrenotazioneService;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.services.UtenteService;

@Controller
@RestController
@RequestMapping("/prenotazione")
@CrossOrigin("http://localhost:4200/")
public class PrenotazioneController {

	@Autowired
	UtenteService utServ;
	@Autowired
	CampoDaGiocoService cgServ;
	@Autowired
	PrenotazioneService prServ;
	@Autowired
	PrenotazioneRepository prRepo;

	@PostMapping
	public ResponseEntity<String> prenotaCampo(@RequestParam("idUtente") int idUtente,
			@RequestParam("idCampo") int idCampo, @RequestParam("dataPrenotazione") String dataPrenotazioneStr,
			@RequestParam("orario") int orario) {
		if (orario < 8 || orario > 23) {
			throw new IllegalArgumentException("Orario non valido. Sono disponibili prenotazioni dalle ore 8 alle 23.");
		}
		try {
			Optional<Utente> utente = utServ.findById(idUtente);
			Optional<CampoDaGioco> campo = cgServ.findById(idCampo);
			LocalDate dataPrenotazione = LocalDate.parse(dataPrenotazioneStr);
			if (dataPrenotazione.isBefore(LocalDate.now())) {
			    throw new IllegalArgumentException("Non è possibile prenotare campi con una data antecedente alla data odierna.");
			}
			List<Prenotazione> lista = prRepo.findByCampoPrenotatoAndDataPrenotazione(idCampo, dataPrenotazione,
					orario);
			if (lista.isEmpty()) {
				prServ.prenotaCampo(utente.get(), campo.get(), dataPrenotazione, orario);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return  new ResponseEntity<>("Hai già una prenotazione in corso per questa data", HttpStatus.CONFLICT);

			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("ID NON TROVATO", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return ResponseEntity.ok("Campo non disponibile per questa data.");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> cancellaPrenotazione(@PathVariable("id") int idPrenotazione) {
	    try {
	        prServ.cancellaPrenotazione(idPrenotazione);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (NoSuchElementException e) {
	        return ResponseEntity.notFound().build();
	    }  
	    	
	}
	
	@GetMapping("/utente/{id}")
	public List<Prenotazione> getPrenotazioniByUserId(@PathVariable int id) {
	    return prServ.findByUserId(id);
	}


}
