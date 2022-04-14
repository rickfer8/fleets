package com.fleets.seguros.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.enuns.PerfilEnum;
import com.fleets.seguros.exception.NaoEncontradoException;
import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.repository.PerfilDAO;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilDAO perfilDAO;
	
	
	public Perfil getById(Long id) {
		Optional<Perfil> retorno = perfilDAO.findById(id);		
		return retorno.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + id ));				
	}
	
	public Perfil getByDescricao(PerfilEnum perfilEnum) {
		Optional<Perfil> retorno = perfilDAO.findDescricao(perfilEnum.name());		
		return retorno.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_DESCRICAO_NAO_ENCONTRADO + perfilEnum.name() ));				
	}

}
