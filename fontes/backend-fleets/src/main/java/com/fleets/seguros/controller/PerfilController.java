package com.fleets.seguros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.service.PerfilService;

@RestController
@RequestMapping(value = "perfis")
public class PerfilController {

	@Autowired
	private PerfilService service;

	@GetMapping
	public ResponseEntity<List<Perfil>> findAll() {
		List<Perfil> perfils = service.findAll();
		return ResponseEntity.ok(perfils);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Perfil> findById(@PathVariable Long id) {
		Perfil perfil = service.getById(id);
		return ResponseEntity.ok(perfil);
	}

}
