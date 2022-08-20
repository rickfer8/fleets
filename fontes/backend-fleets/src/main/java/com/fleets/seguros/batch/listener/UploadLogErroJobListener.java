package com.fleets.seguros.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.exception.batch.DadosInvalidosException;
import com.fleets.seguros.model.batch.UploadLogErro;
import com.fleets.seguros.service.UploadLogErroService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Give a listener a chance to
 * modify the exit status from a step.
 * The value returned will be combined with the normal
 * exit status using ExitStatus.and(ExitStatus)
 *
 * Evento caso seja disparado uma excessão, recupera e armazena
 * uma lista de Log de Erros na tabela UploadLogErro
 *
 * @see StepExecutionListener
 */
@Component
public class UploadLogErroJobListener implements StepExecutionListener {

	@Autowired
	private UploadLogErroService uploadLogErroService;


	@Override
	public void beforeStep(StepExecution stepExecution) {
		// Execucao a ser feita antes da execucao do step
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (stepExecution.getStatus() == BatchStatus.FAILED) {

			Throwable throwable = stepExecution.getFailureExceptions().get(0);
			Throwable throwableCause = throwable instanceof DadosInvalidosException ? throwable : throwable.getCause();

			if (throwableCause instanceof DadosInvalidosException) {
				DadosInvalidosException exception = (DadosInvalidosException) throwableCause;
				List<UploadLogErro> logs = getLogs(exception.getLogs(), stepExecution.getJobExecutionId());
				uploadLogErroService.salvar(logs);
				return ExitStatus.FAILED;
			}
		}
		return ExitStatus.COMPLETED;
	}

	/**
	 * Seta o jobExecutionId em todos os logs
	 * @param logErros
	 * @param jobExecutionId
	 * @return
	 */
	private List<UploadLogErro> getLogs(List<UploadLogErro> logErros, Long jobExecutionId) {
		logErros.forEach(log -> log.setJobExecutionId(BigDecimal.valueOf(jobExecutionId)));
		return logErros;
	}
}
