package com.fleets.seguros.controller;

import org.springframework.batch.core.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fleets.seguros.comandos.UploadArquivo;
import com.fleets.seguros.dto.CorretorDTO;
import com.fleets.seguros.dto.UsuarioDTO;
import com.fleets.seguros.handlers.UploadArquivoHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "upload-arquivo")
@RequiredArgsConstructor
public class UploadArquivoController {

	@Autowired
	private UploadArquivoHandler uploadArquivoHandler;

	/**
	 * Método responsável por receber e importar um arquivo (MultipartFile)
	 * @param file
	 * @param request
	 * @return ResponseEntity<Void>
	 * @throws JobExecutionException 
	 */
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Long> uploadArquivo(@RequestBody MultipartFile file, CorretorDTO corretorDTO) throws JobExecutionException {
		final UploadArquivo comando = new UploadArquivo(corretorDTO, file);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(uploadArquivoHandler.handle(comando));
	}

}
