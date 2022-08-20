package com.fleets.seguros.batch.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.dto.UploadProcessorDTO;
import com.fleets.seguros.model.Cotacao;
import com.fleets.seguros.service.CotacaoService;
import com.fleets.seguros.service.batch.EntidadeValidator;

/**
 * Classe de processamento do upload utilizada pelo framework Spring Batch.
 */
@Component
public class UploadProcessor implements ItemProcessor<UploadProcessorDTO, Cotacao> {
	
	@Autowired
	private EntidadeValidator validator;

	@Autowired
	private CotacaoService cotacaoService;
	
    @Override
    public Cotacao process(UploadProcessorDTO uploadProcessorDTO) {
    	return validator.validarEntidade(cotacaoService.criarEntidade(uploadProcessorDTO));
    }
    
}
