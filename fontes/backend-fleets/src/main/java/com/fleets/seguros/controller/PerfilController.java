package com.fleets.seguros.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.fleets.seguros.converter.PerfilConverter;
import com.fleets.seguros.dto.PerfilDTO;
import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.service.PerfilService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "perfis")
@RequiredArgsConstructor
public class PerfilController {

	private final PerfilService service;
	private final PerfilConverter converter;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<PerfilDTO>> findAll() {
		List<PerfilDTO> perfils = service.findAll().stream()
				.map(converter::convertToDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(perfils);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<PerfilDTO> findById(@PathVariable Long id) {
		Perfil perfil = service.getById(id);
		PerfilDTO dto = converter.convertToDto(perfil);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/filter")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<PerfilDTO>> find(@RequestParam String parametro) {
		List<PerfilDTO> perfils = service.findBySiglaOrDescricao(parametro).stream()
				.map(converter::convertToDto)
				.collect(Collectors.toList());;
		return ResponseEntity.ok(perfils);
	}

	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Void> save(@RequestBody PerfilDTO dto) {
		Perfil perfil = converter.convertToEntity(dto);
		service.save(perfil);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
