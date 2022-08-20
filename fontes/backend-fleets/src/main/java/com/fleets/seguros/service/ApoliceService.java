package com.fleets.seguros.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.dto.UsuarioDTO;
import com.fleets.seguros.enums.AtivoInativoEnum;
import com.fleets.seguros.model.Apolice;
import com.fleets.seguros.model.Arquivo;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.repository.ApoliceRepository;

@Component
public class ApoliceService {

	@Autowired
	private ApoliceRepository apoliceRepository;

	/**
	 * Criar apolice inconsistente.
	 *
	 * @param usuario
	 * 
	 * @param descricao da Apolice
	 * 
	 * @return {@link Apolice}
	 */
	public Apolice criarApolice(UsuarioDTO usuarioDto, Arquivo arquivo) {
		
		Apolice apolice = Apolice.builder()
				.descricao(arquivo.getNomeArquivo()).arquivo(arquivo)
				.dataCriacao(Calendar.getInstance().getTime())
				.usuario(Usuario.builder().id(usuarioDto.getId()).nome(usuarioDto.getNome()).build())
				.status(AtivoInativoEnum.A).build();

		return apoliceRepository.save(apolice);
	}

}
