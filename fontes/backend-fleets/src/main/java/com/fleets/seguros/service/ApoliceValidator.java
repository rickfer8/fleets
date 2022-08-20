package com.fleets.seguros.service;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.batch.reader.ItemMapeamentoValidator;
import com.fleets.seguros.component.UploadMessagesConfiguration;
import com.fleets.seguros.dto.UploadProcessorDTO;
import com.fleets.seguros.model.batch.UploadLogErro;
import com.fleets.seguros.util.ExcelRowUtil;


/**
 * Classe que possui a 
 * responsabilidade de validar
 * a planilha de importacao.
 *
 */

@Component
public class ApoliceValidator {
	
    @Autowired
    private ItemMapeamentoValidator itemMapeamentoValidator;
    
    @Autowired
    private UploadMessagesConfiguration uploadMessagesConfiguration;
    
    private Iterator<Row> iterator;
    
    private Row cabecalho;

    /**
     * Método responsável por verificar se a apolice possui registros.
     *
     * @param sheet the sheet     
     */
    public List<UploadLogErro> validate(Sheet sheet) {
        List<UploadLogErro> logs = new LinkedList<>();
        
        final Row row = sheet.getRow(INTEGER_ONE);
        
        if (row == null || ExcelRowUtil.isRowEmpty(row)) {
			logs.add(UploadLogErro.builder().mensagemErro(uploadMessagesConfiguration.getArquivoSemRegistrosException()).build());
        }      

        return logs;
    }

    public List<UploadLogErro> validatePlanilha(Sheet sheet) {
    	iterator = sheet.iterator();
    	
        List<UploadLogErro> allLogs = new LinkedList<>();
        allLogs.addAll(readFirstLine());

        while (iterator.hasNext()) {
            Row row = iterator.next();
			if (!ExcelRowUtil.isRowEmpty(row)) {
				UploadProcessorDTO linhaDto = UploadProcessorDTO.builder().cabecalho(cabecalho).row(row).build();
				
				allLogs.addAll(itemMapeamentoValidator.validateLine(linhaDto));
			}
        }

        return allLogs;
    }

    public List<UploadLogErro> readFirstLine() {
        if (iterator.hasNext()) {
            cabecalho = iterator.next();
            return itemMapeamentoValidator.validateHeader(cabecalho);
        }

        return new LinkedList<>();
    }
}
