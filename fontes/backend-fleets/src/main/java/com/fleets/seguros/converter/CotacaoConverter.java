package com.fleets.seguros.converter;

import org.springframework.stereotype.Component;

import com.fleets.seguros.dto.CotacaoDTO;
import com.fleets.seguros.model.Cotacao;

@Component
public class CotacaoConverter {

	public Cotacao convertToEntity(CotacaoDTO dto) {
		Cotacao cotacao = new Cotacao();

		return cotacao;
	}

	public CotacaoDTO convertToDto(Cotacao cotacao) {
		CotacaoDTO dto = new CotacaoDTO();

		return dto;
	}

}
