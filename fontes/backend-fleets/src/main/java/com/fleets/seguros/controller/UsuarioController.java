package com.fleets.seguros.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleets.seguros.dto.UsuarioDTO;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.service.PerfilService;
import com.fleets.seguros.service.UsuarioService;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PerfilService perfilService;

	@PostMapping
	public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioDTO usuarioDto) {
		Usuario usuario = usuarioDto.mapper();
		usuario.setPerfil(perfilService.getByDescricao(usuarioDto.getPerfilEnum()));
		Usuario usuarioNovo = usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNovo);
	}

}
