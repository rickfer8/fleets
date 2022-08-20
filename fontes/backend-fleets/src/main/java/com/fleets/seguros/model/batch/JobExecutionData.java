package com.fleets.seguros.model.batch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * JobExecutionData.
 */
@Entity(name = "batch_job_excution_data")
public class JobExecutionData {

	@Id
	@GeneratedValue(generator = "seq_batch_job_excution_data")
	private Long id;
	
	@Column(name = "JOB_EXECUTION_ID")
	private Long jobExecutionId;

	@Column(name = "TOTAL_COUNT")
	private Long total;
	
	@Column(name= "CURRENT_COUNT")
	private Long current;
	
	@Column(name = "FREE_MEMORY")
	private Long freeMemory;
	
	@Column(name = "TOTAL_MEMORY")
	private Long totalMemory;
	
	@Column(name = "MAX_MEMORY")
	private Long maxMemory;

	/**
	 * Instancia um novo job execução data.
	 */
	public JobExecutionData() {}
	
	/**
	 * Instancia um novo job execução data.
	 *
	 * @param jobExecutionId the job execution id
	 * @param total the total
	 */
	public JobExecutionData(Long jobExecutionId, Long total) {
		this.total = total;
		this.jobExecutionId = jobExecutionId;
	}
	
	/**
	 * Retorna o job execução id.
	 *
	 * @return JobExecutionId
	 */
	public Long getJobExecutionId() {
		return jobExecutionId;
	}

	/**
	 * Altera o job execução id.
	 *
	 * @param jobExecutionId the job execução id
	 */
	public void setJobExecutionId(Long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	/**
	 * Retorna o total.
	 *
	 * @return Total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * Altera o total.
	 *
	 * @param total the total
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * Retorna o current.
	 *
	 * @return Current
	 */
	public Long getCurrent() {
		return current;
	}

	/**
	 * Altera o quantidade de dados executadas pelo framework Spring Batch.
	 *
	 * @param current
	 */
	public void setCurrent(Long current) {
		this.current = current;
	}

	/**
	 * Retorna a memória livre.
	 *
	 * @return FreeMemory
	 */
	public Long getFreeMemory() {
		return freeMemory;
	}

	/**
	 * Altera a memória livre.
	 *
	 * @param freeMemory the free memory
	 */
	public void setFreeMemory(Long freeMemory) {
		this.freeMemory = freeMemory;
	}

	/**
	 * Retorna o total de memória.
	 *
	 * @return TotalMemory
	 */
	public Long getTotalMemory() {
		return totalMemory;
	}

	/**
	 * Altera o total de memória.
	 *
	 * @param totalMemory the total memory
	 */
	public void setTotalMemory(Long totalMemory) {
		this.totalMemory = totalMemory;
	}

	/**
	 * Retorna o máximo de memória.
	 *
	 * @return MaxMemory
	 */
	public Long getMaxMemory() {
		return maxMemory;
	}

	/**
	 * Altera o máximo de memória.
	 *
	 * @param maxMemory the max memory
	 */
	public void setMaxMemory(Long maxMemory) {
		this.maxMemory = maxMemory;
	}
	
}
