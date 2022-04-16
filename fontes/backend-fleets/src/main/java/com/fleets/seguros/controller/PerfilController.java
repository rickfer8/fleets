package com.fleets.seguros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.service.PerfilService;

@RestController
@RequestMapping(value = "perfis")
public class PerfilController {

	@Autowired
	private PerfilService service;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<Perfil>> findAll() {
		List<Perfil> perfils = service.findAll();
		return ResponseEntity.ok(perfils);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Perfil> findById(@PathVariable Long id) {
		Perfil perfil = service.getById(id);
		return ResponseEntity.ok(perfil);
	}

	@GetMapping("/filter")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<Perfil>> findPerfil(@RequestParam String sigla, @RequestParam String descricao) {
		List<Perfil> perfils = service.findBySiglaOrDescricao(sigla, descricao);
		return ResponseEntity.ok(perfils);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
