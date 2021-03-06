package com.fleets.seguros.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.exception.CadastroRegistroException;
import com.fleets.seguros.exception.ExcluiRegistroException;
import com.fleets.seguros.exception.NaoEncontradoException;
import com.fleets.seguros.model.Perfil;
import com.fleets.seguros.repository.PerfilRepository;
import com.fleets.seguros.repository.PerfilRepositoryImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PerfilService {

	private final PerfilRepository repository;
	private final PerfilRepositoryImpl repositoryImpl;

	public List<Perfil> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "descricao"));
	}

	public Perfil getById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + id));
	}

	public List<Perfil> findPerfil(String parametro) {
		return repositoryImpl.findPerfil(parametro);
	}

	public void save(Perfil perfil) {
		log.info("salvando perfil {}", perfil);
		try {
			perfil.setSigla(perfil.getSigla().toUpperCase());
			repository.saveAndFlush(perfil);
		} catch (Exception e) {
			log.error("erro ao salvar perfil", e);
			throw new CadastroRegistroException(Constante.ERRO_CADASTRO_REGISTROS);
		}
	}

	@Transactional
	public void deleteById(Long id) {
		log.info("excluindo perfil {}", id);
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			log.error("erro ao excluir perfil {}", id, e);
			throw new ExcluiRegistroException(Constante.ERRO_EXCLUI_REGISTROS + id);
		}
	}

}
