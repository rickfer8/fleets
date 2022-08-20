package com.fleets.seguros.exception.batch;

public class FalhaLeituraArquivoException extends DadosInvalidosException {

	private static final long serialVersionUID = 2373485474028044993L;

	/**
	 * Instancia um novo falha leitura arquivo exception.
	 *
	 * @param mensagem the mensagem
	 */
	public FalhaLeituraArquivoException(String mensagem) {
		super(mensagem);
	}

}
