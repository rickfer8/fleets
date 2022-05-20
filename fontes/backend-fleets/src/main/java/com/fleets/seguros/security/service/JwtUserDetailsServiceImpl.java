package com.fleets.seguros.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.security.jwt.JwtUserFactory;
import com.fleets.seguros.service.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.findByEmail(email);
		return JwtUserFactory.create(usuario);
	}

}
