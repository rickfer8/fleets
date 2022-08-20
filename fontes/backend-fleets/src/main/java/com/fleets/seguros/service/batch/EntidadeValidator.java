package com.fleets.seguros.service.batch;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import com.fleets.seguros.exception.batch.EntidadeInvalidaException;



@Component
public class EntidadeValidator {

	/**
	 * Validar entidade.
	 *
	 * @param <T> the generic type
	 * @param entidade the entidade
	 * @return {@link T}
	 */
	public <T> T validarEntidade(T entidade) {
		final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		final Iterator<ConstraintViolation<T>> iterator = validator.validate(entidade).iterator();
		
		if (iterator.hasNext()) {
			throw new EntidadeInvalidaException(iterator.next());
		}
		return entidade;
	}

}
