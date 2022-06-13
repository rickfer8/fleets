package com.fleets.seguros.converter;

import org.springframework.stereotype.Component;

import com.fleets.seguros.dto.UsuarioDTO;
import com.fleets.seguros.model.Usuario;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioConverter {

	private final PerfilConverter perfilConverter;

	public Usuario convertToEntity(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setId(dto.getId());
		usuario.setAtivo(dto.isAtivo());
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setCpf(dto.getCpf());
		usuario.setSenha(dto.getSenha());
		usuario.setPerfil(perfilConverter.convertToEntity(dto.getPerfil()));
		return usuario;
	}

	public UsuarioDTO convertToDto(Usuario usuario) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(usuario.getId());
		dto.setAtivo(usuario.isAtivo());
		dto.setNome(usuario.getNome());
		dto.setEmail(usuario.getEmail());
		dto.setCpf(usuario.getCpf());
		dto.setPerfil(perfilConverter.convertToDto(usuario.getPerfil()));
		return dto;
	}

}
