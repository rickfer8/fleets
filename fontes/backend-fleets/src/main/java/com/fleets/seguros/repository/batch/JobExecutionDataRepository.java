package com.fleets.seguros.repository.batch;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fleets.seguros.model.batch.JobExecutionData;





/**
 * Repositório de dados de execução de tarefas.
 */
public interface JobExecutionDataRepository extends JpaRepository<JobExecutionData, Long> {
	
    /**
     * Find by job execução id.
     *
     * @param id the id
     * @return {@link JobExecutionData}
     */
    JobExecutionData findFirstByJobExecutionIdOrderByJobExecutionId(Long id);
	
	@Query(value = JobExecutionSQL.CONTAR_JOBS_EM_EXECUCAO_POR_USUARIO, nativeQuery = true)
	BigDecimal countJobsEmExecucaoParaDeterminadoUsuario(@Param("idUsuario") Long idUsuario, @Param("statusEmExecucao") List<String> statusEmExecucao);
	
	@Query(value = "SELECT COUNT(job.JOB_EXECUTION_ID) FROM batch_job_execution job WHERE job.status IN (:statusEmExecucao)", nativeQuery = true)
	BigDecimal countJobsEmExecucao(@Param("statusEmExecucao") List<String> statusEmExecucao);

}
