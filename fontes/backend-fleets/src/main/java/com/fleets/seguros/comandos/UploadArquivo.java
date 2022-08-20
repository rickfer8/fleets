package com.fleets.seguros.comandos;

import org.springframework.web.multipart.MultipartFile;

import com.fleets.seguros.dto.CorretorDTO;


public class UploadArquivo {
	
	private final MultipartFile file;
	
	private final CorretorDTO corretorDTO;
	
	/**
	 * Instancia um novo upload arquivo.
	 *
	 * @param usuarioDto usuario
	 * @param file the file
	 */
	public UploadArquivo(CorretorDTO corretorDTO, MultipartFile file) {		
		this.file = file;		
		this.corretorDTO = corretorDTO;
	}
	
	/**
	 * Retorna o file.
	 *
	 * @return File
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * Retorna o usuario dto.
	 *
	 * @return UsuarioDTO
	 */
	public CorretorDTO getCorretorDTO() {
		return corretorDTO;
	}

}
