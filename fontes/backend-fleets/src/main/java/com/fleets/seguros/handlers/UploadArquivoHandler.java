package com.fleets.seguros.handlers;

import static com.fleets.seguros.enums.TipoArquivo.fromValue;
import static java.util.Objects.isNull;
import static org.apache.commons.io.FilenameUtils.getExtension;

import org.springframework.batch.core.JobExecutionException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fleets.seguros.batch.process.ProcessUploadHandler;
import com.fleets.seguros.batch.process.ProcessUploadParameter;
import com.fleets.seguros.component.UploadMessagesConfiguration;
import com.fleets.seguros.dto.CorretorDTO;
import com.fleets.seguros.exception.batch.ExtensaoNaoSuportadaException;
import com.fleets.seguros.exception.batch.SolicitacaoInvalidaException;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UploadArquivoHandler.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UploadArquivoHandler {
	
	private final UploadMessagesConfiguration exceptionMessages;
	private final ProcessUploadHandler processHandler;
	private final UsuarioService usuarioService;
	
	/**
	 * Handle.
	 *
	 * @param comando the comando
	 * @throws JobExecutionException the job execution exception
	 */
	public Long handle(MultipartFile file, Long idCorretor) throws JobExecutionException {
		try {
			
			Usuario usuario = usuarioService.getById(idCorretor);
			CorretorDTO corretorDTO = new CorretorDTO(usuario.getId(), usuario.getNome()); 

			if (isNull(file)) {
				throw new SolicitacaoInvalidaException(exceptionMessages.getArquivoNaoRecebidoException());
			}

			final String extensao = getExtension(file.getOriginalFilename());

			if (isNull(fromValue(extensao))) {
				throw new ExtensaoNaoSuportadaException(exceptionMessages.getExtensaoNaoSuportadaException(extensao), extensao);
			}

			return processHandler.execute(new ProcessUploadParameter(corretorDTO, file)).getJobInstance().getInstanceId();

		} catch (RuntimeException ex) {
			log.error("[UploadArquivoHandler.handle]", ex);
			throw new SolicitacaoInvalidaException(exceptionMessages.getSolicitacaoInvalidaException());
		}
	}

}
