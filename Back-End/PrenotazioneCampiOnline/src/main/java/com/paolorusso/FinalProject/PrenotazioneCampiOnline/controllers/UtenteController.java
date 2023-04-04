package com.paolorusso.FinalProject.PrenotazioneCampiOnline.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.payloads.UtenteBody;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.services.UtenteService;

@Controller
@RestController
@RequestMapping("/utente")
@CrossOrigin("http://localhost:4200/")
public class UtenteController {
	
	@Autowired 
	private UtenteService utServ;
	
	@Autowired 
	private PasswordEncoder pswE;
	
	@PostMapping("create")
	public ResponseEntity<Object> add(@Valid @RequestBody UtenteBody u) {
		
		Utente u1 = new Utente();
		u1.setNome(u.getNome());
		u1.setCognome(u.getCognome());
		u1.setUsername(u.getUsername());
		u1.setEmail(u.getEmail());
		u1.setPassword(u.getPassword());
		u1.setCitta(u.getCitta());
		utServ.save(u1);
		return new ResponseEntity<>(u1, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Utente>> getAll() {
		List<Utente> list = utServ.findAll();
		
		if( list.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		Optional<Utente> clObj = utServ.findById(id);
		if( !clObj.isPresent()) {
			return new ResponseEntity<>("UTENTE NON TROVATO",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clObj.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		Optional<Utente> clObj = utServ.findById(id);
		
		if( !clObj.isPresent() )  {
			return new ResponseEntity<>("UTENTE NON TROVATO",HttpStatus.NOT_FOUND);
		} else {
			Utente x = clObj.get();
			utServ.delete(x.getId());
		}
		return new ResponseEntity<>(
				String.format("Utente con id %d cancellato!", id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody Utente u) {
        Optional<Utente> utObj = utServ.findById(id);
        if( !utObj.isPresent()) {
            return new ResponseEntity<>("UTENTE NON TROVATO",HttpStatus.NOT_FOUND);
        }
        Utente x = utObj.get();
        x.setNome(u.getNome());
        x.setCognome(u.getCognome());
        x.setEmail(u.getEmail());
        x.setUsername(u.getUsername());
        if(!u.getPassword().equals(x.getPassword())) {
            x.setPassword(pswE.encode(u.getPassword()));
        }
        x.setCitta(u.getCitta());
        
        utServ.save(x);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

	
	
	
}
