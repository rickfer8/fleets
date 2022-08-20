package com.fleets.seguros.util;

import static java.lang.Long.parseLong;
import static java.lang.Math.round;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.StreamSupport.stream;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ExcelUtil.
 */
public class ExcelUtil {

	private ExcelUtil() { }
	
	/**
	 * Carregar primeira aba planilha.
	 *
	 * @param nome
	 * @param extensao
	 * @param stream
	 * @return {@link Sheet}
	 * @throws IOException the IO exception
	 * @throws SQLException the SQL exception
	 * @throws InvalidFormatException the invalid format exception
	 */
	public static Sheet carregarPrimeiraAbaPlanilha(final File arquivoTmp) 
			throws IOException, InvalidFormatException {
		try(Workbook workbook = WorkbookFactory.create(arquivoTmp)) {
			return workbook.getSheetAt(0);
		} 
	}
	
	/**
	 * Retorna o valor celula.
	 *
	 * @param cell the cell
	 * @return ValorCelula
	 */
	public static String getValorCelula(Cell cell) {
		if (isNonNullNumericCell(cell)) {
			return String.valueOf(round(cell.getNumericCellValue()));
		}

		return isNull(cell) ? EMPTY : cell.getStringCellValue().trim();
	}

	/**
	 * Retorna o valor celula date.
	 *
	 * @param cell the cell
	 * @return ValorCelulaDate
	 */
	public static Date getValorCelulaDate(Cell cell) {
		if (isNonNullNumericCell(cell) && isCellDateFormatted(cell)) {
			return cell.getDateCellValue();
		}
		
		return null;
	}
	
	/**
	 * Retorna o valor celula integer.
	 *
	 * @param cell the cell
	 * @return ValorCelulaInteger
	 */
	public static Integer getValorCelulaInteger(Cell cell) {
		final String textoCelula = getValorCelula(cell);
		if (textoCelula.isEmpty()) {
			return null;
		}
		
		return Integer.valueOf(textoCelula);
	}
	
	/**
	 * Retorna o valor celula to big decimal.
	 *
	 * @param cell the cell
	 * @return ValorCelulaToBigDecimal
	 */
	public static BigDecimal getValorCelulaToBigDecimal(Cell cell) {
		if (isNonNullNumericCell(cell)) {
			return BigDecimal.valueOf(cell.getNumericCellValue());
		}
		
		final String textoCelula = getValorCelula(cell);
		if (textoCelula.isEmpty()) {
			return null;
		}
		return BigDecimal.valueOf(Long.valueOf(textoCelula));
	}

	/**
	 * Retorna o valor celula to long.
	 *
	 * @param cell the cell
	 * @return ValorCelulaToLong
	 */
	public static Long getValorCelulaToLong(Cell cell) {
		final String textoCelula = getValorCelula(cell);
		if (textoCelula.isEmpty()) {
			return null;
		}
		
		return parseLong(textoCelula);
	}
	
	/**
	 * Calcula o número total de linhas preenchidas, ignorando a primeira linha, cabeçalho.
	 *
	 * @param sheet the sheet
	 * @return contagem
	 */
	public static long calcularTotalRegistros(Sheet sheet) {
		long total = stream(sheet.spliterator(), false)
			.parallel()
			.filter(ExcelRowUtil::isRowNotEmpty)
			.count();
		
		return total - INTEGER_ONE;
	}
	
	/**
	 * Cria o date style.
	 *
	 * @param workbook
	 *            the workbook
	 * @return {@link CellStyle}
	 */
	public static CellStyle createDateStyle(XSSFWorkbook workbook) {
		final CellStyle cellStyle = workbook.createCellStyle();
		final CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

		return cellStyle;
	}
	
	private static boolean isNonNullNumericCell(Cell cell) {
		return nonNull(cell) && cell.getCellTypeEnum() == NUMERIC;
	}

}