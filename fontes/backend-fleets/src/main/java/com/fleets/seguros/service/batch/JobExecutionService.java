package com.fleets.seguros.service.batch;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleets.seguros.repository.batch.JobExecutionDataRepository;



@Service
public class JobExecutionService {
	
	@Autowired
	private JobExecutionDataRepository jobRepository;
	
	private static final List<String> STATUS_EM_EXECUCAO = Arrays.asList(BatchStatus.STARTED.name(), BatchStatus.STARTING.name());
	
	
	/**
	 * Verifica se existe algum job em execução em um usuario
	 *	
	 * @param usuario
	 * @param jobName
	 * @return
	 */
	public Boolean existeJobEmExecucaoParaDeterminadoUsuario(Long usuario) {
		BigDecimal result = jobRepository.countJobsEmExecucaoParaDeterminadoUsuario(usuario, STATUS_EM_EXECUCAO);
		
		if (result == null) {
			result = BigDecimal.ZERO;
		}
		
		return result.compareTo(BigDecimal.ZERO) > 0;
	}
	
	public Boolean existeJobEmExecucao() {
		BigDecimal result = jobRepository.countJobsEmExecucao(STATUS_EM_EXECUCAO);

		if (result == null) {
			result = BigDecimal.ZERO;
		}

		return result.compareTo(BigDecimal.ZERO) > 0;
	}

}
