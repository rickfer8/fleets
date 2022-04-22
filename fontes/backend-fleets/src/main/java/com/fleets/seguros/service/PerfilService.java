package com.fleets.seguros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.enuns.PerfilEnum;
import com.fleets.seguros.exception.CadastroRegistroException;
import com.fleets.seguros.exception.ExcluiRegistroException;
import com.fleets.seguros.exception.NaoEncontradoException;
import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.repository.PerfilDAO;
import com.fleets.seguros.repository.PerfilDAOImpl;

@Service
public class PerfilService {

	@Autowired
	private PerfilDAO perfilDAO;

	@Autowired
	private PerfilDAOImpl perfilDAOImpl;

	public List<Perfil> findAll() {
		return perfilDAO.findAll(Sort.by(Sort.Direction.ASC, "descricao"));
	}

	public Perfil getById(Long id) {
		Optional<Perfil> retorno = perfilDAO.findById(id);
		return retorno.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + id));
	}

	public List<Perfil> findBySiglaOrDescricao(String sigla, String descricao) {
		return perfilDAOImpl.findBySiglaOrDescricao(sigla, descricao);
	}

	public Perfil save(Perfil perfil) {
		try {
			perfil.setSigla(perfil.getSigla().toUpperCase());
			return perfilDAO.save(perfil);
		} catch (Exception e) {
			throw new CadastroRegistroException(Constante.ERRO_CADASTRO_REGISTROS);
		}
	}

	public Perfil getByDescricao(PerfilEnum perfilEnum) {
		Optional<Perfil> retorno = perfilDAO.findDescricao(perfilEnum.name());
		return retorno.orElseThrow(
				() -> new NaoEncontradoException(Constante.ERRO_DESCRICAO_NAO_ENCONTRADO + perfilEnum.name()));
	}

	@Transactional
	public void deleteById(Long id) {
		try {
			perfilDAO.deleteById(id);
		} catch (Exception e) {
			throw new ExcluiRegistroException(Constante.ERRO_EXCLUI_REGISTROS + id);
		}
	}

}
