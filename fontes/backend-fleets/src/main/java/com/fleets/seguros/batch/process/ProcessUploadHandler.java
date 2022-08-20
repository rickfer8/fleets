package com.fleets.seguros.batch.process;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fleets.seguros.constante.JobExecutionParametrosConstantes;
import com.fleets.seguros.enums.JobNamesEnum;
import com.fleets.seguros.model.Apolice;
import com.fleets.seguros.model.Arquivo;
import com.fleets.seguros.service.ApoliceService;
import com.fleets.seguros.service.ArquivoService;
import lombok.extern.slf4j.Slf4j;

/**
 * ProcessUploadHandler.
 */
@Slf4j
@Service
public class ProcessUploadHandler extends ProcessHandler<ProcessUploadParameter> {
	
	@Autowired
	@Qualifier("asyncJobLauncher")
	private JobLauncher asyncLauncher;

	@Autowired
	private JobRegistry registry;
	
	@Autowired
	private ArquivoService arquivoService;
	
	@Autowired
	private ApoliceService apoliceService;
	
	/**
	 * Método responsável por: Executar a Job de upload de arquivos
	 * 
	 */
	@Override
	public JobExecution executeSpecific(ProcessUploadParameter parametros) throws JobExecutionException {
		final Arquivo arquivo = arquivoService.registrarArquivo(parametros.getMultipart(), parametros.getCorretorDTO());

		final String descricao = parametros.getMultipart().getOriginalFilename();
		
		final Apolice apolice = apoliceService.criarApolice(parametros.getCorretorDTO(), arquivo);

		try {
			return asyncLauncher.run(registry.getJob(getProcessDefinition().name()),
					new JobParametersBuilder().addString(JobExecutionParametrosConstantes.INSTANCE_ID_PARAM_NAME, gerarUuid(), true)							
							.addLong(JobExecutionParametrosConstantes.USUARIO_PARAM_NAME, parametros.getCorretorDTO().getId())
							.addLong(JobExecutionParametrosConstantes.APOLICE_ID_PARAM_NAME, apolice.getId())
							.addString(JobExecutionParametrosConstantes.DESCRICAO_APOLICE_PARAM, descricao)
							.addLong(JobExecutionParametrosConstantes.ARQUIVO_ID_PARAM_NAME, arquivo.getId()).toJobParameters());
		} catch (JobExecutionException e) {			
			log.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Recupera qual é o tipo do processo
	 */
	@Override
	protected JobNamesEnum getProcessDefinition() {
		return JobNamesEnum.UPLOAD;
	}

}
