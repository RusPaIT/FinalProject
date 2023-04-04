package com.paolorusso.FinalProject.PrenotazioneCampiOnline.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Ruolo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoRuolo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories.RuoloRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RuoloService {
	
	@Autowired
	private RuoloRepository rR;
	
	public void save(Ruolo r) {
		Optional<Ruolo> o = getRoleByName(r.getName());
		if (o.isEmpty()) {
			rR.save(r);
			log.info("The Role has been saved in the Database.");			
		}
		else log.info("This Role is already present in the Database.");
	}
	
	public Optional<Ruolo> getRoleByName(TipoRuolo n) {
		return rR.findByName(n);
	}
	
	public Optional<Ruolo> getRoleById(int id) {
		return rR.findById(id);
	}
		
	public List<Ruolo> getAllRoles(){
		return rR.findAll(PageRequest.of(0 , 2000)).getContent();
	}
	
	public Page<Ruolo> getAllRoles(Pageable p) {
		return rR.findAll(p);
	}
	
	public void deleteRoleById(int id) {
		rR.deleteById(id);
	}
	
	public void printList(List<Ruolo> list) {
		for (Ruolo l : list)
			log.info(l.toString());
	}

}
