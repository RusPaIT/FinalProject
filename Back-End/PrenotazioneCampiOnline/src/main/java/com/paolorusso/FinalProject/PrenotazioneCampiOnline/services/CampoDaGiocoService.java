package com.paolorusso.FinalProject.PrenotazioneCampiOnline.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.CampoDaGioco;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoCampo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories.CampoDaGiocoRepository;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories.UtenteRepository;

@Service
public class CampoDaGiocoService {
	
	@Autowired
	private CampoDaGiocoRepository cgRepo;
	@Autowired
	private UtenteRepository utRepo;

	public void save(CampoDaGioco c) {
		cgRepo.save(c);
		System.out.println("Campo salvato nel Database.");
	}
	
	public List<CampoDaGioco> findAll() {
		return cgRepo.findAll();
	}
	
	public Optional<CampoDaGioco> findById(int id) {
		return cgRepo.findById(id);
	}
	
	public void delete(int id) {
		cgRepo.deleteById(id);
	}
	
	 public void modificaCampo(int idCampo, String nuovoNome) {
	        Optional<CampoDaGioco> campoOpt = cgRepo.findById(idCampo);
	        
	        if (campoOpt.isPresent()) {
	            CampoDaGioco campo = campoOpt.get();
	            campo.setNome(nuovoNome);
	            cgRepo.save(campo);
	        } else {
	            throw new NoSuchElementException("Campo non trovato");
	        }
	    }
	
	
	public Optional<List<CampoDaGioco>> filtraInBaseAllaCitta(Integer utenteId) {
	    Optional<Utente> utenteOptional = utRepo.findById(utenteId);

	    if (utenteOptional.isPresent()) {
	        String citta = utenteOptional.get().getCitta();
	        return Optional.of(cgRepo.findByCitta(citta));
	    } else {
	        return Optional.empty();
	    }
	}
	
	public Page<CampoDaGioco> getAll_page(Pageable pageable) {
		return cgRepo.findAll(pageable);
	}
	
	public List<CampoDaGioco> filtraByCitta(String t) {
		return cgRepo.filtraByCitta(t);
	}
	
	public List<CampoDaGioco> filtraByTipoCampo(String t) {
		return cgRepo.filtraByTipoCampo(t);
	}
	
	public List<CampoDaGioco> filtraByNomeCampo(String t) {
		return cgRepo.filtraByNomeCampo(t);
	}

}
