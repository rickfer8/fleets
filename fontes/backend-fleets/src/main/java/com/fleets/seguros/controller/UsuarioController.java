package com.fleets.seguros.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.fleets.seguros.converter.UsuarioConverter;
import com.fleets.seguros.dto.UsuarioDTO;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService service;
	private final UsuarioConverter converter;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> usuarios = service.findAll().stream()
				.map(converter::convertToDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		Usuario usuario = service.getById(id);
		UsuarioDTO dto = converter.convertToDto(usuario);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/filter")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<UsuarioDTO>> find(@RequestParam String parametro) {
		List<UsuarioDTO> usuarios = service.findByNomeOrEmailOrPerfil(parametro).stream()
				.map(converter::convertToDto)
				.collect(Collectors.toList());;
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Void> save(@RequestBody @Valid UsuarioDTO usuarioDto) {
		service.save(usuarioDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
