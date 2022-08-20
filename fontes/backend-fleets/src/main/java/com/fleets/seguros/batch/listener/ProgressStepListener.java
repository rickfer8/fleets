package com.fleets.seguros.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.fleets.seguros.service.batch.JobExecutionDataService;



/**
 * The listener interface for receiving progressStep events.
 * The class that is interested in processing a progressStep
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addProgressStepListener<code> method. When
 * the progressStep event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ProgressStepEvent
 */
public abstract class ProgressStepListener implements StepExecutionListener {

    @Autowired
    private JobExecutionDataService jobExecutionDataService;
    
    /**
     * Retorna o contagem total.
     *
     * @param parameters the parameters
     * @return ContagemTotal
     */
    protected abstract long getContagemTotal(JobParameters parameters);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		final JobExecution execution = stepExecution.getJobExecution();
		Long contagemTotal = getContagemTotal(execution.getJobParameters());
		jobExecutionDataService.registrar(execution.getId(),contagemTotal);
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return stepExecution.getExitStatus();
	}

}
