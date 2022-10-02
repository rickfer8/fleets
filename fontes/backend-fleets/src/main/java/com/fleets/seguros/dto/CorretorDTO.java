package com.fleets.seguros.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorretorDTO {

	private Long id;
	private String nome;

	public CorretorDTO(Long id) {
		super();
		this.id = id;
	}

}
