package com.fleets.seguros.exception.batch;

public class SolicitacaoInvalidaException extends RuntimeException {
	
	private static final long serialVersionUID = 4466629999558748132L;

	/**
	 * Instancia um novo solicitacao invalida exception.
	 *
	 * @param mensagem the mensagem
	 */
	public SolicitacaoInvalidaException(String mensagem) {
		super(mensagem);
	}

}
