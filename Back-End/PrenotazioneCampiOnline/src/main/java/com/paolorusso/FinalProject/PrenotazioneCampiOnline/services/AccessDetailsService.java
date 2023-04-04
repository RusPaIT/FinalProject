package com.paolorusso.FinalProject.PrenotazioneCampiOnline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.AccessDetails;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories.UtenteRepository;


@Service
public class AccessDetailsService implements UserDetailsService {
	
	@Autowired
	UtenteRepository uR;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String x) throws UsernameNotFoundException {
		Utente u = uR.findUserByUsername(x)
				.orElseThrow(() -> new UsernameNotFoundException("No User with Username '" + x + "' was Found."));
		return AccessDetails.build(u);
	}
}