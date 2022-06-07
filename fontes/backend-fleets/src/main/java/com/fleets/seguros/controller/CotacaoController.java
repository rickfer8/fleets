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

import com.fleets.seguros.converter.CotacaoConverter;
import com.fleets.seguros.dto.CotacaoDTO;
import com.fleets.seguros.model.Cotacao;
import com.fleets.seguros.service.CotacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "cotacoes")
@RequiredArgsConstructor
public class CotacaoController {

	private final CotacaoService service;
	private final CotacaoConverter converter;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<CotacaoDTO>> findAll() {
		List<CotacaoDTO> cotacaos = service.findAll().stream()
				.map(converter::convertToDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(cotacaos);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<CotacaoDTO> findById(@PathVariable Long id) {
		Cotacao cotacao = service.getById(id);
		CotacaoDTO dto = converter.convertToDto(cotacao);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/filter")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<List<CotacaoDTO>> find(@RequestParam String parametro) {
		List<CotacaoDTO> cotacoes = service.findCotacao(parametro).stream()
				.map(converter::convertToDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(cotacoes);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Void> save(@RequestBody CotacaoDTO dto) {
		Cotacao cotacao = converter.convertToEntity(dto);
		service.save(cotacao);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
