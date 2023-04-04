package com.paolorusso.FinalProject.PrenotazioneCampiOnline.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.CampoDaGioco;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoCampo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.services.CampoDaGiocoService;

@Controller
@RestController
@RequestMapping("/campi")
@CrossOrigin("http://localhost:4200/")
public class CampoDaGiocoController {
	
	@Autowired
	CampoDaGiocoService cgServ;
	
	@GetMapping("filtra_citta")
	public ResponseEntity<List<CampoDaGioco>> filtraInBaseAllaCitta(@RequestParam("utente_id") Integer utenteId) {
	    Optional<List<CampoDaGioco>> optionalList = cgServ.filtraInBaseAllaCitta(utenteId);

	    if (optionalList.isPresent()) {
	        return new ResponseEntity<>(optionalList.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping()
	public ResponseEntity<List<CampoDaGioco>> getAll() {
		List<CampoDaGioco> list = cgServ.findAll();
		
		if( list.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("page")
	public ResponseEntity<Page<CampoDaGioco>> getAll_page(@RequestParam(defaultValue = "0") int page, 
	                                                @RequestParam(defaultValue = "10") int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<CampoDaGioco> pagedResult = cgServ.getAll_page(paging);
		
		if (pagedResult.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(pagedResult, HttpStatus.OK);
	}
	
	@GetMapping("by_citta_{p}")
	public ResponseEntity<Object> getAll_PageByCitta(@PathVariable String p, @RequestParam(defaultValue = "1000", required = false) 
	Integer pageSize, @RequestParam(defaultValue = "0", required = false) Integer page, @RequestParam(defaultValue = "citta", required = false) String sort) {
		Pageable paging;
		String sorting = p.toUpperCase();
		Sort sortObj = Sort.by(sort);
		if(sorting.equals("DESC")) {
			paging  = PageRequest.of(page, pageSize, sortObj.descending());
		} else if (sorting.equals("ASC")) {
			paging  = PageRequest.of(page, pageSize, sortObj);
		} else {
			return new ResponseEntity<>("Parametro non accettato", HttpStatus.BAD_REQUEST);
		}
		Page<CampoDaGioco> campoDaGioco = cgServ.getAll_page(paging);
		if( campoDaGioco.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(campoDaGioco, HttpStatus.OK);
	}
	
	@GetMapping("by_tipo_di_campo_{p}")
	public ResponseEntity<Object> getAll_PageByTipoCampo(@PathVariable String p, @RequestParam(defaultValue = "1000", required = false) 
	Integer pageSize, @RequestParam(defaultValue = "0", required = false) Integer page, @RequestParam(defaultValue = "tipo_di_campo", required = false) String sort) {
		
//		String sorting = p.toUpperCase();
		Sort sortObj = Sort.by(sort);
		Pageable paging = PageRequest.of(page, pageSize, sortObj);
//		if(sorting.equals("DESC")) {
//			paging  = PageRequest.of(page, pageSize, sortObj.descending());
//		} else if (sorting.equals("ASC")) {
//			paging  = PageRequest.of(page, pageSize, sortObj);
//		} else {
//			return new ResponseEntity<>("Parametro non accettato", HttpStatus.BAD_REQUEST);
//		}
		Page<CampoDaGioco> campoDaGioco = cgServ.getAll_page(paging);
		if( campoDaGioco.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(campoDaGioco, HttpStatus.OK);
	}
	
	@GetMapping("by_nome_{p}")
	public ResponseEntity<Object> getAll_PageByNomeCampo(@PathVariable String p, @RequestParam(defaultValue = "1000", required = false) 
	Integer pageSize, @RequestParam(defaultValue = "0", required = false) Integer page, @RequestParam(defaultValue = "nome", required = false) String sort) {
		Pageable paging;
		String sorting = p.toUpperCase();
		Sort sortObj = Sort.by(sort);
		if(sorting.equals("DESC")) {
			paging  = PageRequest.of(page, pageSize, sortObj.descending());
		} else if (sorting.equals("ASC")) {
			paging  = PageRequest.of(page, pageSize, sortObj);
		} else {
			return new ResponseEntity<>("Parametro non accettato", HttpStatus.BAD_REQUEST);
		}
		Page<CampoDaGioco> campoDaGioco = cgServ.getAll_page(paging);
		if( campoDaGioco.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(campoDaGioco, HttpStatus.OK);
	}
	
	
	@PutMapping("/modifica/{id}")
	public ResponseEntity<String> modificaCampo(@PathVariable("id") int idCampo, @RequestBody CampoDaGioco campoDaModificare) {
	    try {
	        // Controlla se il campo esiste
	        Optional<CampoDaGioco> campoEsistente = cgServ.findById(idCampo);
	        if (!campoEsistente.isPresent()) {
	            return ResponseEntity.notFound().build();
	        }

	        CampoDaGioco campoModificato = campoEsistente.get();
	        campoModificato.setNome(campoDaModificare.getNome());
	        campoModificato.setCitta(campoDaModificare.getCitta());
	        campoModificato.setIndirizzo(campoDaModificare.getIndirizzo());
	        campoModificato.setNumeroTelefono(campoDaModificare.getNumeroTelefono());
	        cgServ.save(campoModificato);
	        return ResponseEntity.ok("Campo modificato con successo.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la modifica del campo: " + e.getMessage());
	    }
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		Optional<CampoDaGioco> clObj = cgServ.findById(id);
		if( !clObj.isPresent()) {
			return new ResponseEntity<>("CAMPO NON TROVATO",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clObj.get(), HttpStatus.OK);
	}

	@GetMapping("filtra_nome_campo={t}")
	public ResponseEntity<Object> filtraByNomeCampo(@PathVariable String t) {
		List<CampoDaGioco> lista = cgServ.filtraByNomeCampo(t);
		if(lista.size() == 0) {
			return  new ResponseEntity<>("Campo non trovato.", HttpStatus.BAD_REQUEST);
		} else {
		return new ResponseEntity<>(lista, HttpStatus.OK);
		}
	}
	
	@GetMapping("filtra_tipo_campo={t}")
	public ResponseEntity<Object> filtraByTipoCampo(@PathVariable String t) {
		List<CampoDaGioco> lista = cgServ.filtraByTipoCampo(t);
		if(lista.size() == 0) {
			return  new ResponseEntity<>("Campo non trovato.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(lista, HttpStatus.OK);
			}
	}


}
