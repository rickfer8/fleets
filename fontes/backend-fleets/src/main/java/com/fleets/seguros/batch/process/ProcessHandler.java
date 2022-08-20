package com.fleets.seguros.batch.process;

import static org.springframework.util.CollectionUtils.arrayToList;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;

import com.fleets.seguros.constante.JobExecutionParametrosConstantes;
import com.fleets.seguros.enums.JobNamesEnum;
import com.fleets.seguros.exception.batch.UsuarioAlreadyScheduledException;
import com.fleets.seguros.service.batch.JobExecutionService;




/**
 * ProcessHandler.
 *
 * @param <T> the generic type
 */
public abstract class ProcessHandler<T extends ProcessUploadParameter> {
	
	@Autowired
	private JobExplorer explorer;
	
	@Autowired
	private JobExecutionService jobExecutionService;
	
	/**
	 * Retorna o process definition.
	 *
	 * @return ProcessDefinition
	 */
	protected abstract JobNamesEnum getProcessDefinition();
	
	/** Gerar uuid. @return {@link String} */
	protected String gerarUuid() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Executa ação especifica das classes filhas ProcessHandlers de acordo com a
	 * necessidade de cada uma.
	 *
	 * @param parametros the parametros
	 * @return {@link JobExecution}
	 * @throws JobExecutionException the job execution exception
	 */
	protected abstract JobExecution executeSpecific(T parametros) throws JobExecutionException;
	
	/**
	 * Executa ação padrão, depois executa a ação especifica da classe filha
	 *
	 * @param parametros the parametros
	 * @return {@link JobExecution}
	 * @throws JobExecutionException the job execution exception
	 */
	public JobExecution execute(T parametros) throws JobExecutionException {
		if (jobExecutionService.existeJobEmExecucaoParaDeterminadoUsuario(parametros.getCorretorDTO().getId())) {
			throw new UsuarioAlreadyScheduledException(parametros.getCorretorDTO().getId());
		}
		
		return executeSpecific(parametros);
	}
	
	/**
	 * Retorna o last executions.
	 *
	 * @param depth the depth
	 * @param idUsuario the Usuario
	 * @param types the types
	 * @return LastExecutions
	 */
	public List<JobExecution> getLastExecutions(int depth, Long idUsuario, BatchStatus[] types) {
		return explorer.getJobInstances(getProcessDefinition().name(), 0, depth).stream().map(explorer::getJobExecutions).flatMap(List::stream)
				.filter(e -> isUsuarioAllowed(e, idUsuario)).filter(e -> isStatusAllowed(e, types)).collect(Collectors.toList());
	}
	
	private boolean isUsuarioAllowed(JobExecution e, Long idUsuario) {
		return idUsuario != null && idUsuario.equals(e.getJobParameters().getLong(JobExecutionParametrosConstantes.USUARIO_PARAM_NAME));
	}
	
	private boolean isStatusAllowed(JobExecution e, BatchStatus[] types) {
		return types != null && types.length == 0 || arrayToList(types).contains(e.getStatus());
	}
}
