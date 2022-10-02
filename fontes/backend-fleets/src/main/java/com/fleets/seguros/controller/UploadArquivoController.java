package com.fleets.seguros.controller;

import org.springframework.batch.core.JobExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fleets.seguros.handlers.UploadArquivoHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "upload-arquivo")
@RequiredArgsConstructor
public class UploadArquivoController {

	private final UploadArquivoHandler uploadArquivoHandler;

	/**
	 * Método responsável por receber e importar um arquivo (MultipartFile)
	 * @param file
	 * @param request
	 * @return ResponseEntity<Void>
	 * @throws JobExecutionException 
	 */
	@PostMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@PreAuthorize("hasAnyAuthority('ADM','DEV')")
	public ResponseEntity<Long> uploadArquivo(@RequestParam("file") MultipartFile file, @PathVariable Long id) throws JobExecutionException {		
		return ResponseEntity.status(HttpStatus.CREATED).body(uploadArquivoHandler.handle(file, id));
	}

}
