package com.paolorusso.FinalProject.PrenotazioneCampiOnline.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import javax.validation.Valid;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.AccessDetails;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Ruolo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoRuolo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.payloads.JwtResponse;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.payloads.LoginRequest;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.payloads.MessageResponse;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.payloads.SignupRequest;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories.RuoloRepository;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories.UtenteRepository;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.utils.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {
	
	@Autowired
	AuthenticationManager aM;
	@Autowired
	UtenteRepository uR;
	@Autowired
	RuoloRepository rR;
	@Autowired
	PasswordEncoder pE;
	@Autowired
	JwtUtils jU;

	@PostMapping("/login")	
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication a = aM.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		return returnToken(a);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest RegisterRequest) {
		if (uR.existsByUsername(RegisterRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (uR.existsByEmail(RegisterRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		
		// Create new user's account
		Utente user = Utente.builder()
				.nome(RegisterRequest.getNome())
				.cognome(RegisterRequest.getCognome())		
				.email(RegisterRequest.getEmail())
				.username(RegisterRequest.getUsername())
				.password(pE.encode(RegisterRequest.getPassword()))
				.citta(RegisterRequest.getCitta())
				.build();

		uR.save(user);
		return new ResponseEntity("Utente registrato con successo", HttpStatus.OK);
	}


	public ResponseEntity<?> returnToken(Authentication a) {
		SecurityContextHolder.getContext().setAuthentication(a);
		String jwt = jU.generateJwtToken(a);		
		AccessDetails uD = (AccessDetails) a.getPrincipal();		
		List<String> ruoli = uD.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, uD.getId(), uD.getUsername(), uD.getEmail(),uD.getCitta(), ruoli));
	}

	
}
