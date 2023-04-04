package com.paolorusso.FinalProject.PrenotazioneCampiOnline.configurations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.CampoDaGioco;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Ruolo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoCampo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoRuolo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;


@Configuration
public class Beans {

	@Bean
	@Scope("prototype")
	public Utente utente(String n, String c, String u, String e, String pw, String ct) {
		return Utente.builder().nome(n).cognome(c).username(u).email(e).password(pw).citta(ct).build();
	}
	
	@Bean
	@Scope("prototype")
	public Ruolo ruolo(TipoRuolo r) {
		return Ruolo.builder().name(r).build();
	}
	
	@Bean
	@Scope("prototype")
	public CampoDaGioco campo(String n, String ct, String i, String nt, TipoCampo tc) {
		    
		return CampoDaGioco.builder().nome(n).citta(ct).indirizzo(i).numeroTelefono(nt).tipoCampo(tc).build();
	}
	
}
