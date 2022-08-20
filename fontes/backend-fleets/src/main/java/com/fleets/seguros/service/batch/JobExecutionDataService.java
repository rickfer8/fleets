package com.fleets.seguros.service.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleets.seguros.model.batch.JobExecutionData;
import com.fleets.seguros.repository.batch.JobExecutionDataRepository;




@Service
public class JobExecutionDataService {

	@Autowired
	private JobExecutionDataRepository repository;
	
	/**
	 * Registrar.
	 *
	 * @param jobExecutionId the job execution id
	 * @param total the total
	 * @return {@link JobExecutionData}
	 */
	public JobExecutionData registrar(Long jobExecutionId, Long total) {
		return repository.save(new JobExecutionData(jobExecutionId, total));
	}
	
	/**
	 * Atualizar.
	 *
	 * @param jobExecutionId the job execution id
	 * @param current the current
	 * @return {@link JobExecutionData}
	 */
	public JobExecutionData atualizar(Long jobExecutionId, Long current) {
		final JobExecutionData job = repository.findFirstByJobExecutionIdOrderByJobExecutionId(jobExecutionId);
		final Runtime runtime = Runtime.getRuntime();
		job.setCurrent(current);
		job.setFreeMemory(runtime.freeMemory());
		job.setTotalMemory(runtime.totalMemory());
		job.setMaxMemory(runtime.maxMemory());
		
		return repository.save(job);
	}
	
}
