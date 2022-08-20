package com.fleets.seguros.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleets.seguros.model.batch.UploadLogErro;
import com.fleets.seguros.repository.UploadLogErroRepository;

@Service
public class UploadLogErroService {
	
	@Autowired
	private UploadLogErroRepository uploadLogErroRepository;
	
	/**
	 * Salva um registro de log de erro ao fazer upload de uma planilha que possua um
	 * erro de validacao
	 *  
	 * @param uploadLogErro
	 */
	public void salvar(List<UploadLogErro> uploadLogErro) {
		uploadLogErroRepository.saveAll(uploadLogErro);
	}

	public List<UploadLogErro> buscaValidacoesPorJob(BigDecimal jobExecutionId){
		return uploadLogErroRepository.findByJobExecutionId(jobExecutionId);
	}

	public UploadLogErro criarLog(Integer numeroLinha, String nomeColuna, String mensagemErro, BigDecimal jobExecutionId) {
		UploadLogErro uploadLogErro = new UploadLogErro();
		uploadLogErro.setJobExecutionId(jobExecutionId);
		uploadLogErro.setLinha(numeroLinha);
		uploadLogErro.setMensagemErro(mensagemErro);
		uploadLogErro.setTituloColuna(nomeColuna);

		return uploadLogErro;
	}

}