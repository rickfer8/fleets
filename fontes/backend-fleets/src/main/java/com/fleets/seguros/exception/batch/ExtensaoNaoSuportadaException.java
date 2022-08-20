package com.fleets.seguros.exception.batch;

import static java.lang.String.format;

public class ExtensaoNaoSuportadaException extends RuntimeException {	

	private static final long serialVersionUID = -8063920106067313135L;

	/**
	 * Instancia um novo extensao nao suportada exception.
	 *
	 * @param mensagem the mensagem
	 * @param extensao the extensao
	 */
	public ExtensaoNaoSuportadaException(String mensagem, String extensao) {
		super(format(mensagem, extensao));
	}

}
