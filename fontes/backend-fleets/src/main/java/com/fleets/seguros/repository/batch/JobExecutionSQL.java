package com.fleets.seguros.repository.batch;

/**
 *
 *
 * JobExecutionSQL.
 *
 * Classe utilitária para guardar SQLs nativos
 * Constante de sql nativo pra buscar os jobs do spring batch em execução.
 *
 */
public final class JobExecutionSQL {

	private JobExecutionSQL() {
		throw new IllegalStateException("Classe utilitária");
	}

	private static final String COUNT_JOB_EXECUTION_ID_FROM_JOB_EXECUTION = "SELECT COUNT(job.JOB_EXECUTION_ID) FROM batch_job_execution job ";
	
	public static final String CONTAR_JOBS_EM_EXECUCAO_POR_USUARIO = 
			COUNT_JOB_EXECUTION_ID_FROM_JOB_EXECUTION + 
				" INNER JOIN batch_job_execution_PARAMS params ON params.JOB_EXECUTION_ID = job.JOB_EXECUTION_ID " + 
				" WHERE job.status IN (:statusEmExecucao) " + 
				" AND (params.KEY_NAME = 'usuario' AND params.long_val = :idUsuario)";
	
	

	
}
