package com.fleets.seguros.handlers;

import static com.fleets.seguros.enums.TipoArquivo.fromValue;
import static java.util.Objects.isNull;
import static org.apache.commons.io.FilenameUtils.getExtension;

import org.springframework.batch.core.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fleets.seguros.batch.process.ProcessUploadHandler;
import com.fleets.seguros.batch.process.ProcessUploadParameter;
import com.fleets.seguros.comandos.UploadArquivo;
import com.fleets.seguros.component.UploadMessagesConfiguration;
import com.fleets.seguros.exception.batch.ExtensaoNaoSuportadaException;
import com.fleets.seguros.exception.batch.SolicitacaoInvalidaException;


import lombok.extern.slf4j.Slf4j;

/**
 * UploadArquivoHandler.
 */
@Component
@Slf4j
public class UploadArquivoHandler {
	
	@Autowired
	private UploadMessagesConfiguration exceptionMessages;
	
	@Autowired
	private ProcessUploadHandler processHandler;
	
	/**
	 * Handle.
	 *
	 * @param comando the comando
	 * @throws JobExecutionException the job execution exception
	 */
	public Long handle(UploadArquivo comando) throws JobExecutionException {
		try {
			final MultipartFile multipart = comando.getFile();

			if (isNull(multipart)) {
				throw new SolicitacaoInvalidaException(exceptionMessages.getArquivoNaoRecebidoException());
			}

			final String extensao = getExtension(multipart.getOriginalFilename());

			if (isNull(fromValue(extensao))) {
				throw new ExtensaoNaoSuportadaException(exceptionMessages.getExtensaoNaoSuportadaException(extensao), extensao);
			}

			return processHandler.execute(new ProcessUploadParameter(comando.getCorretorDTO(), multipart)).getJobInstance().getInstanceId();

		} catch (RuntimeException ex) {
			log.error("[UploadArquivoHandler.handle]", ex);
			throw new SolicitacaoInvalidaException(exceptionMessages.getSolicitacaoInvalidaException());
		}
	}

}
