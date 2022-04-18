package com.fleets.seguros.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.model.Usuario;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(Usuario usuario) {
		JwtUser jwtUser = new JwtUser(usuario.getId().toString(), usuario.getEmail(), usuario.getSenha(),
				mapToGrantedAuthority(usuario.getPerfil()));
		return jwtUser;
	}

	private static List<GrantedAuthority> mapToGrantedAuthority(Perfil perfil) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfil.getSigla()));
		return authorities;
	}

}
