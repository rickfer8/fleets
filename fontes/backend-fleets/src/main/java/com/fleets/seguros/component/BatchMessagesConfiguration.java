package com.fleets.seguros.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:i18n/batch_messages_pt.properties")
@Component
public class BatchMessagesConfiguration {
	
	@Value( "${erro.ao.executar.processo}" )
	private String erroAoExecutarProcesso;
	
	@Value( "${processo.completado.por.outra.execucao}" )
	private String processoCompletadoPorOutraExecucao;
	
	@Value( "${processo.marcado.como.falha.na.inicializacao}" )
	private String processoMarcadoComoFalhaNaInicializacao;
	
	@Value( "${ajustamento.removido}" )
	private String ajustamentoRemovido;
	
	@Value( "${arquivotmp.removido}" )
	private String arquivoTmpRemovido;
	
	@Value( "${calculo.removido}" )
	private String calculoRemovido;

	@Value("${integracao.falha}")
	private String integracaoFalha;

	/**
	 * Retorna o erro ao executar processo.
	 *
	 * @return ErroAoExecutarProcesso
	 */
	public String getErroAoExecutarProcesso() {
		return erroAoExecutarProcesso;
	}

	/**
	 * Retorna o processo completado por outra execucao.
	 *
	 * @return ProcessoCompletadoPorOutraExecucao
	 */
	public String getProcessoCompletadoPorOutraExecucao() {
		return processoCompletadoPorOutraExecucao;
	}

	/**
	 * Retorna o processo marcado como falha na inicializacao.
	 *
	 * @return ProcessoMarcadoComoFalhaNaInicializacao
	 */
	public String getProcessoMarcadoComoFalhaNaInicializacao() {
		return processoMarcadoComoFalhaNaInicializacao;
	}

	/**
	 * Retorna o ajustamento removido.
	 *
	 * @param id the id
	 * @return AjustamentoRemovido
	 */
	public String getAjustamentoRemovido(Long id) {
		return incluirParametros(ajustamentoRemovido, id);
	}

	/**
	 * Retorna o arquivo tmp removido.
	 *
	 * @param id the id
	 * @return ArquivoTmpRemovido
	 */
	public String getArquivoTmpRemovido(Long id) {
		return incluirParametros(arquivoTmpRemovido, id);
	}

	/**
	 * Retorna o calculo removido.
	 *
	 * @param id the id
	 * @return CalculoRemovido
	 */
	public String getCalculoRemovido(Long id) {
		return incluirParametros(calculoRemovido, id);
	}

	/**
	 * Retorna a mensagem da falha na integração
	 * @param id do calculo
	 * @return
	 */
	public String getIntegracaoFalha(Long id) {
		return incluirParametros(integracaoFalha, id);
	}

	private String incluirParametros(String message, Long id) {
		return String.format(message, id);
	}
}
