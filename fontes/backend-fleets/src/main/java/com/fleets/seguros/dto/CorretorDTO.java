package com.fleets.seguros.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorretorDTO {

	private Long id;
	private String nome;

	public CorretorDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

}
