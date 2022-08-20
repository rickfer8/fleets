package com.fleets.seguros.batch.process;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.listener.CompositeJobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.batch.listener.UploadJobListener;
import com.fleets.seguros.exception.batch.ExceptionJobListener;



/**
 * Agrupador de listeners do processo de Upload.
 *  A ordem de registro é inversa à de notificação.
 */
@Component
public class UploadCompositeJobListener extends CompositeJobExecutionListener {
	
	@Autowired
	private UploadJobListener jobListener;
	
	@Autowired
	private ExceptionJobListener exceptionListener;

	/**
	 * Método responsável por:
	 * Iniciar a Job.
	 */
	@PostConstruct
	public void init() {
		register(jobListener);
		register(exceptionListener);
	}

}
