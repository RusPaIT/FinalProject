package com.paolorusso.FinalProject.PrenotazioneCampiOnline.payloads;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private String email;
	private String citta;
	private List<String> roles;

	public JwtResponse(String accessToken, int id, String username, String email, String citta, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.citta = citta;
		this.roles = roles;
	}
}