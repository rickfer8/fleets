package com.fleets.seguros.component;

import static java.lang.String.format;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * Arquivo que possui as mensagens que serão usadas pelo log de erro do Upload
 *
 */
@PropertySource("classpath:i18n/exception_messages_pt.properties")
@Component
public class UploadMessagesConfiguration {

	@Getter
	@Value("${upload.falha.salvar.arquivo.temporario.exception}")
	private String falhaSalvarArquivoTemporarioException;
	
    @Getter
    @Value( "${upload.arquivo.nao.recebido.exception}" )
    private String arquivoNaoRecebidoException;
    
    @Value( "${upload.extensao.nao.suportada.exception}" )
    private String extensaoNaoSuportadaException;
    
	@Value( "${solicitacao.invalida.exception}" )
	private String solicitacaoInvalidaException;
	
	@Value( "${arquivo.removido}" )
	private String arquivoTemporarioRemovido;

	@Getter
	@Value("${upload.arquivo.sem.registros.exception}")
	private String arquivoSemRegistrosException;
	
    @Value( "${upload.falha.leitura.arquivo.exception}" )
    private String falhaLeituraArquivoException;
    
    /**
     * Retorna o extensao nao suportada exception.
     *
     * @return ExtensaoNaoSuportadaException
     */
    public String getExtensaoNaoSuportadaException(String extensao) {
        return format(extensaoNaoSuportadaException, extensao);
    }
    
	/**
	 * Retorna o solicitacao invalida exception.
	 *
	 * @return SolicitacaoInvalidaException
	 */
	public String getSolicitacaoInvalidaException() {
		return solicitacaoInvalidaException;
	}
	
	/**
	 * Retorna o arquivo removido.
	 *
	 * @param id the id
	 * @return ArquivoRemovido
	 */
	public String getArquivoRemovido(Long id) {
		return incluirParametros(arquivoTemporarioRemovido, id);
	}
	
	private String incluirParametros(String message, Long id) {
		return String.format(message, id);
	}
	
    /**
     * Retorna o falha leitura arquivo exception.
     *
     * @return FalhaLeituraArquivoException
     */
    public String getFalhaLeituraArquivoException(String nomeArquivo, String extensao) {
        return format(falhaLeituraArquivoException, nomeArquivo, extensao);
    }

}
