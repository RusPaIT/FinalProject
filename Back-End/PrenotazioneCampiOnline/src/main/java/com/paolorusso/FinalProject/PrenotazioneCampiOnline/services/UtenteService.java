package com.paolorusso.FinalProject.PrenotazioneCampiOnline.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories.UtenteRepository;


@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository uR;
	
	
	public void save(Utente u) {
		uR.save(u);
		System.out.println("Utente salvato nel DataBase.");
	}
	
	public Optional<Utente> getUserByUsername(String u) {
		return uR.findUserByUsername(u);
	}
	
	public Optional<Utente> getUserByEmail(String e) {
		return uR.findUserByEmail(e);
	}	
	
	public List<Utente> findAll() {
		return uR.findAll();
	}
	
	public List<Utente> getAllUsers(){
		return uR.findAll(PageRequest.of(0 , 2000)).getContent();
	}
	
	public Page<Utente> getAllUsers(Pageable p) {
		return uR.findAll(p);
	}
	
	public void printList(List<Utente> list) {
		for (Utente l : list)
			System.out.println(l.toString());
	}
	
	public Optional<Utente> findById(int id) {
		return uR.findById(id);
	}
	
	public void delete(int id) {
		uR.deleteById(id);
	}


}
