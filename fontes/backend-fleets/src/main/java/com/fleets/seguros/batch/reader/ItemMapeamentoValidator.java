package com.fleets.seguros.batch.reader;

import static com.fleets.seguros.constante.Constante.ERRO_NOT_FOUND;
import static com.fleets.seguros.constante.Constante.LOG_UPLOAD_DADO_TAMANHO;
import static com.fleets.seguros.constante.Constante.LOG_UPLOAD_DADO_TIPO_INVALIDO;
import static com.fleets.seguros.util.DateUtil.formataData;
import static com.fleets.seguros.util.NumberUtil.isBigDecimalValid;
import static java.lang.Integer.parseInt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.constante.Constante;
import com.fleets.seguros.dto.UploadProcessorDTO;
import com.fleets.seguros.model.batch.UploadLogErro;
import com.fleets.seguros.service.batch.ArquivoMapeamentoService;
import com.fleets.seguros.util.DateUtil;
import com.fleets.seguros.util.NumberUtil;

import lombok.NoArgsConstructor;

/**
 * Classe responsável pelas validações por tipo, obrigatoriedade e tamanho dos itens da planilha
 */
@Component
@NoArgsConstructor
public class ItemMapeamentoValidator {
	
    @Autowired
    private ArquivoMapeamentoService mapeamentoService;
  
    private ArquivoMapeamento mapeamento;
    
    @PostConstruct
    private void init() {
        mapeamento = mapeamentoService.getMapeamento();
    }

    /**
     * Método responsável por verificar se as colunas no cabeçalho estão no mapeamento json.
     *
     * @param linhaCabecalho
     * @param spe
     * @return
     */
    public List<UploadLogErro> validateHeader(Row linhaCabecalho) {
        List<UploadLogErro> listUploadLogErro = new LinkedList<>();
        
        for (int i = 0; i < linhaCabecalho.getLastCellNum(); i++) {
            if (linhaCabecalho.getCell(i) != null) {

                final Object nomeColuna = linhaCabecalho.getCell(i);
                final ItemMapeamento itemMapeamento = mapeamento.getItemMapeamento(nomeColuna.toString().trim());
                
                if (itemMapeamento == null) {
                    String mensagemErro = montarMensagemErroItemMapeamento(nomeColuna.toString());
                    listUploadLogErro.add(new UploadLogErro(null, nomeColuna.toString().trim(), mensagemErro));
                }
            }
        }

        return listUploadLogErro;
    }
    
    private String montarMensagemErroItemMapeamento(String nomeColuna) {
    	if (StringUtils.isBlank(nomeColuna)) {
    		return Constante.ITEM_MAPEAMENTO_VAZIO;
    	}
    	
    	return String.format("Coluna: %s %s", nomeColuna, ERRO_NOT_FOUND);
    }

    /**
     * Método responsável por válidar as linhas por tipo, tamanho, obrigatoriedade e item existente
     *
     * @param linhaDto    
     * @return
     */
    public List<UploadLogErro> validateLine(UploadProcessorDTO linhaDto) {

        List<UploadLogErro> listUploadLogErro = new LinkedList<>();
        
        for (int i = 0; i < linhaDto.getQuantidadeColunas(); i++) {
        	
            final String nomeColuna = linhaDto.getCabecalho(i).getStringCellValue().trim();
            final ItemMapeamento itemMapeamento = mapeamento.getItemMapeamento(nomeColuna);
            
            if (itemMapeamento != null) {
                String conteudo = linhaDto.getCelula(i) == null ? "" : linhaDto.getCelula(i).toString();

                List<UploadLogErro> listaValidacoes = validarItemMapeamento(itemMapeamento, linhaDto, nomeColuna, conteudo);

                if (!listaValidacoes.isEmpty()) {
                    listUploadLogErro.addAll(listaValidacoes);
                }
            }
        }
        
        return listUploadLogErro;
    }

	/**
	 * Metodo que armazena os logs de erro das validações dos dados informados na planilha
	 * 
	 * @param itemMapeamento
	 * @param linhaDto
	 * @param nomeColuna
	 * @param conteudo
	 * @param spe
	 * @return
	 */
	private List<UploadLogErro> validarItemMapeamento(ItemMapeamento itemMapeamento, UploadProcessorDTO linhaDto, 
			String nomeColuna, String conteudo) {
		
		List<UploadLogErro> listUploadLogErro = new ArrayList<>();		
		
		listUploadLogErro.add(validarPorType(itemMapeamento, nomeColuna, linhaDto, conteudo));
		listUploadLogErro.add(validarPorSize(itemMapeamento, nomeColuna, linhaDto, conteudo));
		
		listUploadLogErro.removeIf(Objects::isNull);
		return listUploadLogErro.stream().filter(log -> log.getMensagemErro() != null).collect(Collectors.toList());
	} 	
	
    /**
     * Verifica se o valor informado é valido de acordo com o tipo do campo
     */
	protected UploadLogErro validarPorType(ItemMapeamento itemMapeamento, String nomeColuna, 
			UploadProcessorDTO linhaDto,  String conteudo) {
		
		if (StringUtils.isBlank(conteudo)) {
    		return null;
    	}
		
		Boolean campoValido = true;
		
		switch (itemMapeamento.getType()) {
			case NUMBER:
				campoValido = NumberUtil.isBigDecimalValid(conteudo) != null;
				break;
			case DATE:
				campoValido = DateUtil.getFormatoData(conteudo).isPresent();
				break;
			default:
				break;
		}
		
		if (!campoValido) {
			return new UploadLogErro(linhaDto.getNumeroLinha(), nomeColuna, LOG_UPLOAD_DADO_TIPO_INVALIDO);
		}
		
		return null;
	}

    /**
     * Verifica tamanho dos campos
     * @param itemMapeamento
     * @param conteudo
     * @param linhaDto
     * @param nomeColuna
     * @return
     */
	protected UploadLogErro validarPorSize(ItemMapeamento itemMapeamento, String nomeColuna, 
			UploadProcessorDTO linhaDto, String conteudo) {
    	
    	if (StringUtils.isBlank(conteudo)) {
    		return null;
    	}
    	
    	Boolean tamanhoValido = true;
    	
		switch (itemMapeamento.getType()) {
			case NUMBER:
                if (isBigDecimalValid(conteudo) != null) {
                    BigDecimal valorMaximo = new BigDecimal(itemMapeamento.getSize());
                    BigDecimal valorConteudo = isBigDecimalValid(conteudo);
                    tamanhoValido = (valorMaximo.compareTo(valorConteudo) == 0 || valorMaximo.compareTo(valorConteudo) >= 1);
                }
				break;
			case DATE:
				String dataFormatada = (formataData(conteudo) != null ? formataData(conteudo) : "");
				tamanhoValido = validarSeTamanhoConteudoMaiorQueTamanhoMapeamento(dataFormatada, itemMapeamento);
				break;
			case STRING:
				tamanhoValido = validarSeTamanhoConteudoMaiorQueTamanhoMapeamento(conteudo, itemMapeamento);
				break;
			default:
		}
		
		if (!tamanhoValido) {
			return new UploadLogErro(linhaDto.getNumeroLinha(), nomeColuna, LOG_UPLOAD_DADO_TAMANHO);
		}
		
		return null;
    }
	
	private Boolean validarSeTamanhoConteudoMaiorQueTamanhoMapeamento(String conteudo, ItemMapeamento mapeamento) {
		return conteudo.trim().length() <= parseInt(mapeamento.getSize());
	}
}
