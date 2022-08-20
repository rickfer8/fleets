package com.fleets.seguros.service.mapeamento;

import static com.fleets.seguros.constante.Constante.COLUNA_CIDADE;
import static com.fleets.seguros.constante.Constante.COLUNA_COMBUSTIVEL;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleets.seguros.batch.reader.ArquivoMapeamento;
import com.fleets.seguros.batch.reader.ItemMapeamento;
import com.fleets.seguros.dto.UploadProcessorDTO;
import com.fleets.seguros.model.Cotacao;
import com.fleets.seguros.model.mapeamento.MapeamentoMapperService;
import com.fleets.seguros.service.batch.ArquivoMapeamentoService;
import com.fleets.seguros.util.StringUtil;




/**
 * Serviço de mapeamento das colunas do registro ajustamento e sua planilha de importação
 */
@Service
public class RegistroCotacaoMapeamentoService {
	
    @Autowired
    private ArquivoMapeamentoService mapeamentoService;

    @Autowired
    private MapeamentoMapperService mapperService;
    
    
    /**
     * Retorna o objeto Cotacao referente a linha da planilha
     *
     * @param row
     * @return
     */
    public Cotacao getRegistroCotacao(UploadProcessorDTO row) {
        JSONObject json = getJsonRegistroCotacao(row);
        return mapperService.getRegistroCotacaoFromJson(json.toString());
    }

    /**
     * Retorna o JSON referente a Cotacao da planilha
     *
     * @param row
     * @return
     */
    private JSONObject getJsonRegistroCotacao(UploadProcessorDTO row) {
        JSONObject response = new JSONObject();
        ArquivoMapeamento mapeamento = mapeamentoService.getMapeamento();

        for (int i = 0; i < row.getQuantidadeColunas(); i++) {
        	
        	final Cell celula = row.getCelula(i);
        	final String nomeColuna = row.getCabecalho(i).getStringCellValue().trim();
        	final ItemMapeamento itemMapeamento = mapeamento.getItemMapeamento(nomeColuna);
        	
        	final Object conteudo = getConteudoCelula(celula, nomeColuna, itemMapeamento);
        	putField(response, itemMapeamento, conteudo);
        }
        
        return response;
    }
    
    public Object getConteudoCelula(Cell celula, String nomeColuna, ItemMapeamento itemMapeamento) {
    	Object conteudo = new Object();
    	
    	if (isNull(celula)) {
        	conteudo = "";
        } else {
            if (nonNull(itemMapeamento)) {
            	if (COLUNA_CIDADE.equalsIgnoreCase(nomeColuna.toUpperCase())) {
            		celula.setCellValue(StringUtil.removerAcentos(celula.toString()));
            	}
                if(COLUNA_COMBUSTIVEL.equalsIgnoreCase(nomeColuna)){
                    celula.setCellValue(celula.getStringCellValue().toUpperCase());
                }
            	
            	conteudo = itemMapeamento.lerCelula(celula);
            }
        }
    	
    	return conteudo;
    }
    
    /**
     * Adiciona um novo campo no json
     *
     * @param response
     * @param map
     * @param value
     */
    private void putField(JSONObject response, final ItemMapeamento map, Object value) {
        Object valueConvertido = convert(map, value);

        if (map.getValorArray().length > 1) {
            checkFieldExist(response, map.getValorArray()[0]);
            response.getJSONObject(map.getValorArray()[0]).put(map.getValorArray()[1], valueConvertido);
        } else {
            response.put(map.getValorArray()[0], valueConvertido);
        }
    }

    /**
     * Realiza conversões.
     * As conversões devem ser informadas no mapeamento.json
     *
     * @param map
     * @param value
     * @return
     */
	private Object convert(ItemMapeamento map, Object value) {

		if (nonNull(map.getConverter())) {
			final boolean isIntegerConverter = "IntegerConverter".equalsIgnoreCase(map.getConverter());
			final boolean isBigDecimalConverter = "BigDecimalConverter".equalsIgnoreCase(map.getConverter());
			return outrosConvert(value, isIntegerConverter, isBigDecimalConverter);
		}
		return value;
	}

    private Object outrosConvert(Object value, boolean isIntegerConverter, boolean isBigDecimalConverter) {
        if (isIntegerConverter && nonNull(value) && !"".equals(value)) {
            return new BigDecimal(value.toString()).intValue();
        } else if (isBigDecimalConverter && nonNull(value) && !"".equals(value)) {
            return new BigDecimal(value.toString()).toBigInteger();
        }

        return value;
    }

    /**
     * Verifica se o campo existe
     *
     * @param response
     * @param field
     */
    private void checkFieldExist(JSONObject response, String field) {
        if (!response.has(field)) {
            response.put(field, new JSONObject());
        }
    }

}
