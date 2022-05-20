package com.fleets.seguros.dto;

import com.fleets.seguros.model.Perfil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {

	private Long id;
	private String sigla;
	private String descricao;

	public PerfilDTO(Long id) {
		super();
		this.id = id;
	}

	public Perfil mapper() {
		return Perfil.builder().id(id).sigla(sigla).descricao(descricao).build();
	}

}
