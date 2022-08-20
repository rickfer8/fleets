package com.fleets.seguros.batch.process;

import org.springframework.web.multipart.MultipartFile;

import com.fleets.seguros.dto.CorretorDTO;

/**
 * ProcessUploadParameter.
 */
public class ProcessUploadParameter {
	
	private final MultipartFile multipart;
	private final CorretorDTO corretorDTO;
	
	/**
	 * Instancia um novo process upload parameter.
	 *
	 * @param usuarioDTO 
	 * @param multipart the multipart
	 */
	public ProcessUploadParameter(CorretorDTO corretorDTO, MultipartFile multipart) {	
		this.multipart = multipart;
		this.corretorDTO = corretorDTO;
	}

	/**
	 * Retorna o multipart.
	 *
	 * @return Multipart
	 */
	public MultipartFile getMultipart() {
		return multipart;
	}

	public CorretorDTO getCorretorDTO() {
		return corretorDTO;
	}

}
