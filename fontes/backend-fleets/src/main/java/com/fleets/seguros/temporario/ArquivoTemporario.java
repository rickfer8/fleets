package com.fleets.seguros.temporario;

import java.io.InputStream;

import com.fleets.seguros.model.Usuario;

/**
 * 
 * Interface para arquivos temporarios
 *
 */
public interface ArquivoTemporario {
	
	String getNomeArquivo();

	String getTipoExtensao();
	
	Usuario getUsuario();
	
	String getPath();
	
	InputStream getFile();

}
