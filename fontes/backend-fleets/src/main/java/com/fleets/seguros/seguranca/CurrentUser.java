package com.fleets.seguros.seguranca;

import com.fleets.seguros.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {

	private String token;
	private Usuario usuario;

}
