package com.fleets.seguros.security.jwt;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class JwtAutenticationRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String login;
	
	@Getter
	@Setter
	private String senha;
	
	public JwtAutenticationRequest() {		
	}

	public JwtAutenticationRequest(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	

}
