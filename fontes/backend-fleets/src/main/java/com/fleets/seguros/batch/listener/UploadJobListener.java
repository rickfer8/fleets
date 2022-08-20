package com.fleets.seguros.batch.listener;


import static org.springframework.batch.core.ExitStatus.COMPLETED;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.component.UploadMessagesConfiguration;
import com.fleets.seguros.constante.JobExecutionParametrosConstantes;
import com.fleets.seguros.service.ArquivoService;


/**
 * The listener interface for receiving uploadJob events.
 * The class that is interested in processing a uploadJob
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addUploadJobListener<code> method. When
 * the uploadJob event occurs, that object's appropriate
 * method is invoked.
 *
 * @see UploadJobEvent
 */
@Component
public class UploadJobListener implements JobExecutionListener {

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private UploadMessagesConfiguration messagesConfiguration;

    @Override
    public void beforeJob(JobExecution jobExecution) {
    	// Execucao a ser feita antes da execucao do job
    }

    @Override
    public void afterJob(JobExecution jobExecution) {       
        final Long arquivoId = jobExecution.getJobParameters().getLong(JobExecutionParametrosConstantes.ARQUIVO_ID_PARAM_NAME);

        if (!isJobCompleted(jobExecution)) {
            if (arquivoService.removerArquivo(arquivoId)) {
                jobExecution.setExitStatus(jobExecution.getExitStatus().addExitDescription(messagesConfiguration.getArquivoRemovido(arquivoId)));
            }   
        }
    }

    private boolean isJobCompleted(JobExecution jobExecution) {
        return COMPLETED.getExitCode().equals(jobExecution.getExitStatus().getExitCode());
    }

}
