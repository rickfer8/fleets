package com.fleets.seguros.exception.batch;

import javax.validation.ConstraintViolation;

public class EntidadeInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo entidade invalida exception.
	 *
	 * @param constraint the constraint
	 */
	public EntidadeInvalidaException(ConstraintViolation<?> constraint) {
		super(String.format("%s: %s", constraint.getPropertyPath(), constraint.getMessage()));
	}
}
