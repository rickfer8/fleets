package com.fleets.seguros.comandos;

import org.springframework.web.multipart.MultipartFile;

import com.fleets.seguros.dto.UsuarioDTO;


public class UploadArquivo {
	
	private final MultipartFile file;
	
	private final UsuarioDTO usuarioDTO;
	
	/**
	 * Instancia um novo upload arquivo.
	 *
	 * @param usuarioDto usuario
	 * @param file the file
	 */
	public UploadArquivo(UsuarioDTO usuarioDTO, MultipartFile file) {		
		this.file = file;		
		this.usuarioDTO = usuarioDTO;
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
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

}
