package com.fleets.seguros.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fleets.seguros.dto.UsuarioDTO;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.service.UsuarioService;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> usuarios = service.findAll();
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario usuario = service.getById(id);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/filter")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<Usuario>> find(@RequestParam String parametro) {
		List<Usuario> usuarios = service.findByNomeOrEmailOrPerfil(parametro);
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioDTO usuarioDto) {
		Usuario usuario = service.save(usuarioDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
