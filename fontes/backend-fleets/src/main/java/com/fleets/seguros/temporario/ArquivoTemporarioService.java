package com.fleets.seguros.temporario;

import static com.fleets.seguros.constante.Constante.PASTA_TEMPORARIA_ARQUIVO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.springframework.stereotype.Service;

/**
 * 
 * Serviço gerenciador de arquivos temporários
 *
 */
@Service
public class ArquivoTemporarioService {
	
	/**
	 * Salva o arquivo no ambiente local e inclui o id recebido
	 * @param arquivo
	 * @return Long, arquivoRemotoId
	 * @throws IOException 
	 * @throws JSONException
	 */
	public String create(ArquivoTemporario arquivo) throws IOException {
		String tempDirectoryPath = System.getProperty("java.io.tmpdir");
		
		createFolder(tempDirectoryPath);
		final File arquivoTmp = File.createTempFile(arquivo.getNomeArquivo(), arquivo.getTipoExtensao(), new File(tempDirectoryPath, PASTA_TEMPORARIA_ARQUIVO));
		IOUtils.copy(arquivo.getFile(), new FileOutputStream(arquivoTmp));
		
		return arquivoTmp.getAbsolutePath();
	}
	
	/**
	 * Busca o arquivo na pasta temporária através do caminho recebido
	 * @param arquivo
	 * @return InputStream
	 * @throws IOException 
	 * @throws JSONException
	 */
	public File getArquivo(ArquivoTemporario arquivo) {
		return new File(arquivo.getPath());
	}
	
	/**
	 * Delete do arquivo na pasta temporária através do caminho recebido
	 * @param arquivo
	 */
	public void delete(ArquivoTemporario arquivo) {
		new File(arquivo.getPath()).deleteOnExit();
	}
	
	/**
	 * Cria a pasta caso não exista
	 * @param tempDirectoryPath
	 */
	private void createFolder(String tempDirectoryPath) {
		final File arquivoTmp = new File(tempDirectoryPath, PASTA_TEMPORARIA_ARQUIVO);
		arquivoTmp.mkdirs();
	}

}
