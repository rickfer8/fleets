package com.fleets.seguros.util;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.poi.ss.usermodel.CellType.BLANK;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelRowUtil {

	private ExcelRowUtil() {}
	
	/**
	 * Verifica se row not empty.
	 *
	 * @param row the row
	 * @return boolean
	 */
	public static boolean isRowNotEmpty(Row row) {
		return !isRowEmpty(row);
	}
	
	/**
	 * Verifica se row empty.
	 *
	 * @param row the row
	 * @return boolean
	 */
	public static boolean isRowEmpty(Row row) {
		if (isNull(row) || row.getLastCellNum() <= 0) {
			return true;
		}

		for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
			final Cell cell = row.getCell(cellNum);
			if (nonNull(cell) && cell.getCellTypeEnum() != BLANK && isNotBlank(cell.toString())) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Cria a celula para a linha.
	 *
	 * @param row
	 * @param posicao the posicao
	 * @param tituloColuna the titulo coluna
	 */
	public static void createCellToRow(XSSFRow row, Integer posicao, String valorColuna) {
		XSSFCell cell = row.createCell(posicao);
		cell.setCellValue(valorColuna);
	}
	
}
