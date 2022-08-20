package com.fleets.seguros.exception.batch;

public class UsuarioAlreadyScheduledException extends RuntimeException {

	private static final long serialVersionUID = -242771305560270889L;
	
	public static final String MESSAGE = "Já existe outro job rodando para esse Usuário.";

    /**
     * Instancia um novo spe already scheduled exception.
     *
     * @param spe the spe
     */
    public UsuarioAlreadyScheduledException(Long usuario) {
        super(MESSAGE + ": " + usuario);
    }

}
