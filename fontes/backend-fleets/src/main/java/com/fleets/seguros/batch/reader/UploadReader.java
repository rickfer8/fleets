package com.fleets.seguros.batch.reader;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fleets.seguros.constante.JobExecutionParametrosConstantes;
import com.fleets.seguros.dto.UploadProcessorDTO;
import com.fleets.seguros.exception.batch.DadosInvalidosException;
import com.fleets.seguros.model.Arquivo;
import com.fleets.seguros.model.batch.UploadLogErro;
import com.fleets.seguros.repository.ArquivoRepository;
import com.fleets.seguros.service.ApoliceValidator;
import com.fleets.seguros.service.ArquivoService;
import com.fleets.seguros.util.ExcelRowUtil;
import com.fleets.seguros.util.ExcelUtil;


/**
 * Classe de leitura do processo de upload utilizada pelo framework Spring Batch.
 */
@Component
@StepScope
public class UploadReader implements ItemReader<UploadProcessorDTO> {

    @Value("#{jobParameters['" + JobExecutionParametrosConstantes.ARQUIVO_ID_PARAM_NAME + "']}")
    private Long arquivoId;   
    
    @Autowired
    private ArquivoService service;

    @Autowired
    private ArquivoRepository repository;

    @Autowired
    private ApoliceValidator apoliceValidator; 
    
    private Iterator<Row> iterator;
    
    private Row cabecalho;    

    
    /**
     * Inicializa o iterator a ser lido pelo {@link #read()} e pula o primeiro
     * registro que deve ser cabeçalho
     *
     * @throws IOException
     * @throws SQLException
     * @throws InvalidFormatException
     */
	@PostConstruct
	protected void configure() throws IOException, InvalidFormatException {		
		
		final Arquivo arquivo = repository.findById(arquivoId).get();
		
		final Sheet sheet = ExcelUtil.carregarPrimeiraAbaPlanilha(service.getConteudo(arquivo));
		
		List<UploadLogErro> logs = new LinkedList<>();
		
		List<UploadLogErro> uploadLogErroList = apoliceValidator.validate(sheet);
		
		if (!uploadLogErroList.isEmpty()) {
			logs.addAll(uploadLogErroList);
			throw new DadosInvalidosException(logs);
		} else {
			logs = apoliceValidator.validatePlanilha(sheet);
			
			if (!logs.isEmpty()) {
				throw new DadosInvalidosException(logs);
			}
		}
		
		iterator = sheet.iterator();
		
		readFirstLine();
	} 
    
    
    private void readFirstLine() {
        if (iterator.hasNext()) {
            cabecalho = iterator.next();
        }
    }
    
    @Override
    public UploadProcessorDTO read() {
        if (!iterator.hasNext()) {
            return null;
        }

        final Row row = iterator.next();

        if (ExcelRowUtil.isRowNotEmpty(row)) {
            
            return UploadProcessorDTO
                    .builder()
                    .cabecalho(cabecalho)
                    .row(row)                   
                    .build();
        }

        return null;
    }

}
