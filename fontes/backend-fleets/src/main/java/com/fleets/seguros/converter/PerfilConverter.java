package com.fleets.seguros.converter;

import org.springframework.stereotype.Component;

import com.fleets.seguros.dto.PerfilDTO;
import com.fleets.seguros.model.Perfil;

@Component
public class PerfilConverter {

	public Perfil convertToEntity(PerfilDTO dto) {
		Perfil perfil = new Perfil();
		perfil.setId(dto.getId());
		perfil.setDescricao(dto.getDescricao());
		perfil.setSigla(dto.getSigla());
		return perfil;
	}

	public PerfilDTO convertToDto(Perfil perfil) {
		PerfilDTO dto = new PerfilDTO();
		dto.setId(perfil.getId());
		dto.setDescricao(perfil.getDescricao());
		dto.setSigla(perfil.getSigla());
		return dto;
	}

}
