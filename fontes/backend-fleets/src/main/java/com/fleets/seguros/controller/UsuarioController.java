package com.fleets.seguros.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleets.seguros.dto.LoginDTO;
import com.fleets.seguros.dto.UsuarioDTO;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.seguranca.CurrentUser;
import com.fleets.seguros.seguranca.JwtManager;
import com.fleets.seguros.service.PerfilService;
import com.fleets.seguros.service.UsuarioService;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired 
	private AuthenticationManager authManager;
	
	@Autowired 
	private JwtManager jwtManager;
	
	@PostMapping
	public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioDTO usuarioDto){
		Usuario usuario = usuarioDto.mapper();
		usuario.setPerfil(perfilService.getByDescricao(usuarioDto.getPerfilEnum()));
		Usuario usuarioNovo = usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNovo);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUser> login(@RequestBody @Valid LoginDTO login){
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());		
		Authentication auth = authManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		org.springframework.security.core.userdetails.User usuarioSpring = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		String email = usuarioSpring.getUsername();
		List<String> roles =  usuarioSpring.getAuthorities()
				   						.stream()
				   						.map(authority -> authority.getAuthority())
				   						.collect(Collectors.toList());
		
		String jwt = jwtManager.createToken(email, roles);
		Usuario usuario = usuarioService.findByEmail(email);
		CurrentUser currentUser = new CurrentUser(jwt, usuario);
		
		return ResponseEntity.ok(currentUser);
	}

}
