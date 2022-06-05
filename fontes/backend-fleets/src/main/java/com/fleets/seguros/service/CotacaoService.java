package com.fleets.seguros.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.exception.CadastroRegistroException;
import com.fleets.seguros.exception.ExcluiRegistroException;
import com.fleets.seguros.exception.NaoEncontradoException;
import com.fleets.seguros.model.Cotacao;
import com.fleets.seguros.repository.CotacaoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CotacaoService {

	private final CotacaoRepository repository;

	public List<Cotacao> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public Cotacao getById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException(Constante.ERRO_ID_NAO_ENCONTRADO + id));
	}

	public void save(Cotacao cotacao) {
		log.info("salvando cotacao {}", cotacao);
		try {
			repository.saveAndFlush(cotacao);
		} catch (Exception e) {
			log.error("erro ao salvar cotacao", e);
			throw new CadastroRegistroException(Constante.ERRO_CADASTRO_REGISTROS);
		}
	}

	@Transactional
	public void deleteById(Long id) {
		log.info("excluindo cotacao {}", id);
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			log.error("erro ao excluir cotacao {}", id, e);
			throw new ExcluiRegistroException(Constante.ERRO_EXCLUI_REGISTROS + id);
		}
	}

}
