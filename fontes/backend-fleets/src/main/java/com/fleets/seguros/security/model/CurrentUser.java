package com.fleets.seguros.security.model;

import com.fleets.seguros.model.Usuario;

import lombok.Getter;
import lombok.Setter;

public class CurrentUser {

	@Getter
	@Setter
	private String token;

	@Getter
	@Setter
	private Usuario usuario;

	public CurrentUser() {
	}

	public CurrentUser(String token, Usuario usuario) {
		super();
		this.token = token;
		this.usuario = usuario;
	}

}
