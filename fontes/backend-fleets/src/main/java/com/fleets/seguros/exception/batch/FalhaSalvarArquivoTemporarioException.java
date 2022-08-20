package com.fleets.seguros.exception.batch;

public class FalhaSalvarArquivoTemporarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo falha salvar arquivo temporario exception.
	 *
	 * @param mensagem the mensagem
	 */
	public FalhaSalvarArquivoTemporarioException(String mensagem) {
		super(mensagem);
	}
}
