package com.fleets.seguros.batch.process;

import static com.fleets.seguros.enums.JobNamesEnum.UPLOAD;
import static org.springframework.batch.core.BatchStatus.FAILED;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleets.seguros.component.BatchMessagesConfiguration;
import com.fleets.seguros.constante.JobExecutionParametrosConstantes;
import com.fleets.seguros.service.ArquivoService;
import com.fleets.seguros.service.batch.JobExecutionService;


/**
 * JobService.
 */
@Component
public class JobService {
	
	@Autowired
	private BatchMessagesConfiguration batchMessages;

    @Autowired
    private JobExplorer explorer;

    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private ArquivoService arquivoService;
    
    @Autowired
    private JobExecutionService jobExecutionService;
    
    /**
     * Fail uncompleted executions.
     */
    public void failUncompletedExecutions() {
        explorer.getJobNames().forEach(this::failUncompletedExecution);
    }  

    /**
     * Verifica se falhou execução.
     *
     * @param execution the execution
     * @return boolean
     */
    static boolean isFailedExecution(JobExecution execution) {
        return BatchStatus.FAILED.equals(execution.getStatus());
    }
    
    static boolean isValidacaoEmProcessamento(Long idUsuario, JobExecution execution) {
        return execution.getJobParameters().getLong(JobExecutionParametrosConstantes.USUARIO_PARAM_NAME).equals(idUsuario);
    }
    
    private void failUncompletedExecution(String name) {
		if (jobExecutionService.existeJobEmExecucao()) {
			explorer.findRunningJobExecutions(name).stream().filter(job -> job.getStatus().isRunning()).forEach(this::failUncompletedExecution);
		}
    }

    private void failUncompletedExecution(JobExecution execution) {
        final String jobName = execution.getJobInstance().getJobName();
        
        final ExitStatus exitStatus = clearDataAndGetExitStatus(execution, jobName);
        
        execution.setStatus(FAILED);
        execution.setExitStatus(exitStatus);
        jobRepository.update(execution);

        execution.getStepExecutions().forEach(step -> {
            step.setStatus(FAILED);
            step.setExitStatus(exitStatus);
        });
        
        execution.getStepExecutions().forEach(jobRepository::update);
    }

	private ExitStatus clearDataAndGetExitStatus(JobExecution execution, final String jobName) {
		ExitStatus exitStatus = ExitStatus.UNKNOWN.addExitDescription(batchMessages.getProcessoMarcadoComoFalhaNaInicializacao());

		if (UPLOAD.name().equals(jobName)) {
			final Long idArquivo = execution.getJobParameters().getLong(JobExecutionParametrosConstantes.ARQUIVO_ID_PARAM_NAME);
			if (arquivoService.removerArquivo(idArquivo)) {
				exitStatus = exitStatus.addExitDescription(batchMessages.getArquivoTmpRemovido(idArquivo));
			}
		}
		return exitStatus;
	}
}
