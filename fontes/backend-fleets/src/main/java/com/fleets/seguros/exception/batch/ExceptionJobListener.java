package com.fleets.seguros.exception.batch;


import static org.apache.commons.lang3.StringUtils.defaultIfBlank;
import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.springframework.batch.core.ExitStatus.FAILED;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.component.BatchMessagesConfiguration;



/**
 * The listener interface for receiving exceptionJob events.
 * The class that is interested in processing a exceptionJob
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addExceptionJobListener<code> method. When
 * the exceptionJob event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ExceptionJobEvent
 */
@Component
public class ExceptionJobListener implements JobExecutionListener {
	
	@Autowired
	private BatchMessagesConfiguration batchMessages;

	@Override
	public void beforeJob(JobExecution execution) { return;}

    @Override
    public void afterJob(JobExecution execution) {
        final ExitStatus status = execution.getExitStatus();
        final String code = status.getExitCode();
        
        if (FAILED.getExitCode().equals(code)) {
        	final String description = status.getExitDescription();
        	
            if (description.contains(EntidadeInvalidaException.class.getName())) {
            	execution.setExitStatus(new ExitStatus(code, getMensagemResumida(EntidadeInvalidaException.class, description)));
            	
            } else { 
            	execution.setExitStatus(new ExitStatus(code, getMensagemPadrao()));
            }
        }
    }

	private String getMensagemPadrao() {
		return batchMessages.getErroAoExecutarProcesso();
	}

	private String getMensagemResumida(Class<? extends Throwable> exception, String mensagemOriginal) {
		return defaultIfBlank(substringBetween(mensagemOriginal, exception.getName() + ": ", "\n"), mensagemOriginal);
	}
}
