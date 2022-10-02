package com.fleets.seguros.service;

import static org.apache.commons.io.FilenameUtils.getExtension;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fleets.seguros.component.UploadMessagesConfiguration;
import com.fleets.seguros.dto.CorretorDTO;
import com.fleets.seguros.exception.batch.FalhaSalvarArquivoTemporarioException;
import com.fleets.seguros.model.Arquivo;
import com.fleets.seguros.model.Usuario;
import com.fleets.seguros.repository.ArquivoRepository;
import com.fleets.seguros.temporario.ArquivoTemporarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ArquivoService {
	
	private final ArquivoRepository repository;
	
	@Autowired
	private ArquivoTemporarioService arquivoTemporarioService;
	
	@Autowired
	private UploadMessagesConfiguration exceptionMessages;
	
	/**
	 * Registrar arquivo.
	 *
	 * @param file the file
	 * @return {@link Arquivo}
	 */
	public Arquivo registrarArquivo(MultipartFile file, CorretorDTO corretor) {
		final String filename = file.getOriginalFilename();

		try {
			final Arquivo arquivo = new Arquivo();
			arquivo.setNomeArquivo(filename);
			arquivo.setTipoExtensao(getExtension(filename));
			arquivo.setDataCriacao(Calendar.getInstance().getTime());
			arquivo.setFile(file.getInputStream());			
			arquivo.setNomeUsuario(corretor.getNome());
			arquivo.setUsuario(Usuario.builder().id(corretor.getId()).nome(corretor.getNome()).build());
			arquivo.setPath(arquivoTemporarioService.create(arquivo));

			return repository.save(arquivo);

		} catch (IOException e) {
			log.error("[ArquivoTemporarioBatchService.registrarArquivo]", e);
			throw new FalhaSalvarArquivoTemporarioException(exceptionMessages.getFalhaSalvarArquivoTemporarioException());

		}
	}
	
	/**
	 * Baixar arquivo.
	 *
	 * @param ArquivoTemporarioBatch arquivo
	 * @return InputStream
	 * @throws IOException
	 */
	public File getConteudo(Arquivo arquivo) {
		return arquivoTemporarioService.getArquivo(arquivo);
	}

	/**
	 * Remover arquivo.
	 *
	 * @param arquivoId the arquivo id
	 * @return boolean
	 */
	public boolean removerArquivo(Long arquivoId) {
		final Optional<Arquivo> arquivoOptional = repository.findById(arquivoId);

		if (arquivoOptional.isPresent()) {
			arquivoTemporarioService.delete(arquivoOptional.get());			
			return true;
		}
		return false;
	}

}
