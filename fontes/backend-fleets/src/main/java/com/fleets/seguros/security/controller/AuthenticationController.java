package com.fleets.seguros.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.security.jwt.JwtAutenticationRequest;
import com.fleets.seguros.security.jwt.JwtTokenUtil;
import com.fleets.seguros.security.model.CurrentUser;
import com.fleets.seguros.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(value = "/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAutenticationRequest jwtAutenticationRequest) {
		ResponseEntity<?> responseEntity;
		try {
			final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					jwtAutenticationRequest.getLogin(), jwtAutenticationRequest.getSenha());
			final Authentication authentication = authenticationManager
					.authenticate(usernamePasswordAuthenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAutenticationRequest.getLogin());
			final String token = jwtTokenUtil.generateToken(userDetails);
			final Usuario usuario = usuarioService.findByEmail(jwtAutenticationRequest.getLogin());
			usuario.setSenha(null);
			responseEntity = ResponseEntity.ok(new CurrentUser(token, usuario));
		} catch (Exception e) {
			responseEntity = ResponseEntity.ok(null);
		}
		return responseEntity;
	}

	@PostMapping(value = "/refresh")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest httpServletRequest) {
		ResponseEntity<?> responseEntity;
		String token = httpServletRequest.getHeader("Authorization");
		String login = jwtTokenUtil.getUserNameFromToken(token);
		final Usuario usuario = usuarioService.findByEmail(login);

		if (jwtTokenUtil.canTokenBenRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			responseEntity = ResponseEntity.ok(new CurrentUser(refreshedToken, usuario));
		} else {
			responseEntity = ResponseEntity.badRequest().body(null);
		}

		return responseEntity;
	}

}
