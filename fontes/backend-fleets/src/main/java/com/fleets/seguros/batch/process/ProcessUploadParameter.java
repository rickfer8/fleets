package com.fleets.seguros.batch.process;

import org.springframework.web.multipart.MultipartFile;

import com.fleets.seguros.dto.UsuarioDTO;

/**
 * ProcessUploadParameter.
 */
public class ProcessUploadParameter {
	
	private final MultipartFile multipart;
	private final UsuarioDTO usuarioDTO;
	
	/**
	 * Instancia um novo process upload parameter.
	 *
	 * @param usuarioDTO 
	 * @param multipart the multipart
	 */
	public ProcessUploadParameter(UsuarioDTO usuarioDTO, MultipartFile multipart) {	
		this.multipart = multipart;
		this.usuarioDTO = usuarioDTO;
	}

	/**
	 * Retorna o multipart.
	 *
	 * @return Multipart
	 */
	public MultipartFile getMultipart() {
		return multipart;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

}
