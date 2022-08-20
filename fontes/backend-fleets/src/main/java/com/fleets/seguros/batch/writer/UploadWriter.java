package com.fleets.seguros.batch.writer;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fleets.seguros.constante.JobExecutionParametrosConstantes;
import com.fleets.seguros.model.Apolice;
import com.fleets.seguros.model.Cotacao;
import com.fleets.seguros.repository.ApoliceRepository;
import com.fleets.seguros.repository.CotacaoRepository;

/**
 * Componente do processo de upload, é responsável por escrever novos
 * ajustamentos no banco de dados, após terem passado pelos componentes de
 * leitura e transformação.
 */
@StepScope
@Component
public class UploadWriter implements ItemWriter<Cotacao> {
	
    @Value("#{jobParameters['" + JobExecutionParametrosConstantes.APOLICE_ID_PARAM_NAME + "']}")
    private Long apoliceId;

    @Autowired
    private ApoliceRepository apoliceRepository;

    @Autowired
    private CotacaoRepository repository;

    /**
     * Métodos responsável por:
     * Salvar os itens no repositório.
     */
    @Override
    public void write(List<? extends Cotacao> itensApolice) {
    	
        final Apolice apolice = apoliceRepository.findById(apoliceId).get();
        
        itensApolice.forEach(item -> {
        	item.setApolice(apolice);        	
        	repository.save(item);
        });
    }

}