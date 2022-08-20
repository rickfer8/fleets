package com.fleets.seguros.dto;

import lombok.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Classe responsável por armazenar as informações do upload
*/
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UploadProcessorDTO {

	private final Row cabecalho;
	private final Row row;

	public long getQuantidadeColunas(){
		return cabecalho.getLastCellNum();
	}

	/**
	 * Retorna o cabeçalho da coluna informada
	 * @param coluna
	 * @return
	 */
	public Cell getCabecalho(int coluna){
		return cabecalho.getCell(coluna);
	}

	/**
	 * Retorna o valor da coluna informada
	 * @param coluna
	 * @return
	 */
	public Cell getCelula(int coluna){
		return row.getCell(coluna);
	}

	/**
	 * Retorna a linha do contéudo
	 * @return Integer
	 */
	public Integer getNumeroLinha(){
		return row.getRowNum() + 1;
	}
}
