package com.paolorusso.FinalProject.PrenotazioneCampiOnline.payloads;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotBlank
	private String username;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	@NotBlank
	private String citta;
}
