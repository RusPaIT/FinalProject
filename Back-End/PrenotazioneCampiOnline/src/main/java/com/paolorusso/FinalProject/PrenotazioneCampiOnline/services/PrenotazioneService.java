package com.paolorusso.FinalProject.PrenotazioneCampiOnline.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.CampoDaGioco;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Prenotazione;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories.PrenotazioneRepository;

@Service
public class PrenotazioneService {

	@Autowired
	private PrenotazioneRepository prRepo;
	
	public void save(Prenotazione p) {
		prRepo.save(p);
		System.out.println("Prenotazione salvata.");
	}
		
	public void printList(List<Prenotazione> list) {
		for (Prenotazione p : list)
			System.out.println(p.toString());
	}
	
	public List<Prenotazione> findAll() {
		return prRepo.findAll();
	}
	
	
	public Optional<Prenotazione> findById(int id) {
		return prRepo.findById(id);
	}
	
	public void cancellaPrenotazione(int id) {
        prRepo.deleteById(id);
    }
	
	public void prenotaCampo(Utente utente, CampoDaGioco campoDaPrenotare, LocalDate dataPrenotazione, int orario) {

	    Prenotazione prenotazione = new Prenotazione();
	    prenotazione.setUtente(utente);
	    prenotazione.setCampoPrenotato(campoDaPrenotare);
	    prenotazione.setDataPrenotazione(dataPrenotazione);
	    prenotazione.setOrario(orario);
	    prRepo.save(prenotazione);
	}	
	

	public List<Prenotazione> findByUserId(int userId) {
		
	       return prRepo.findByUserId(userId);
	}

	

}
