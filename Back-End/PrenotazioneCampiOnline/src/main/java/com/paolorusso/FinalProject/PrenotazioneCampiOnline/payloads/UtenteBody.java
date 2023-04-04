package com.paolorusso.FinalProject.PrenotazioneCampiOnline.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtenteBody {

	private int id;
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;
    private String citta;
	
}
