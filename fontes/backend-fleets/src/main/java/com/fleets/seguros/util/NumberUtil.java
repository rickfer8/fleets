package com.fleets.seguros.util;

import static java.math.BigDecimal.*;
import static java.math.RoundingMode.HALF_UP;
import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * NumberUtil.
 */
public class NumberUtil {

	public static final int SCALE_2 = 2;
	public static final int SCALE_8 = 8;
	public static final BigDecimal ONE_SCALED = scaleBigDecimal(ONE, SCALE_8);
	public static final BigDecimal ONE_HUNDRED = roundBigDecimal(100);
	
	private NumberUtil() { }
	
	/**
	 * Safe parse.
	 *
	 * @param value the value
	 * @return {@link int}
	 */
	public static int safeParse(Long value) {
		return value == null ? null : value.intValue();
	}
	
	/**
	 * Round big decimal.
	 *
	 * @param value the value
	 * @return {@link BigDecimal}
	 */
	public static BigDecimal roundBigDecimal(Integer value) {
		return valueOf(value).setScale(SCALE_2, HALF_UP);
	}
	
	/**
	 * Round big decimal.
	 *
	 * @param value the value
	 * @return {@link BigDecimal}
	 */
	public static BigDecimal roundBigDecimal(Double value) {
		return valueOf(value).setScale(SCALE_2, HALF_UP);
	}
	
	/**
	 * Scale big decimal.
	 *
	 * @param value the value
	 * @param scale the scale
	 * @return {@link BigDecimal}
	 */
	public static BigDecimal scaleBigDecimal(BigDecimal value, int scale) {
		return value.setScale(scale, HALF_UP);
	}
	
	/**
	 * Verifica se o Number passado é positivo
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isValorPositivo(Number number) {
		return number != null && number.doubleValue() > 0;
	}
	
	/**
	 * Verifica se o Number passado é nulo ou zero
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean isNullOrZero(Number valor) {
		return (valor == null || (DOUBLE_ZERO.compareTo(valor.doubleValue()) == DOUBLE_ZERO));
	}
	
	/**
	 * Verifica se o Number passado é nulo ou diferente de zero
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean isNullOrDifferentZero(Number valor) {
		return (valor == null || (valor.doubleValue() > DOUBLE_ZERO) || (valor.doubleValue() < DOUBLE_ZERO));
	}
	
	public static boolean isNotNullAndMaiorQueZero(Number valor) {
		return valor != null && valor.doubleValue() > 0;
	}
	
	public static String castNumberToString(Number valor) {
		return valor == null ? StringUtils.EMPTY : valor.toString();
	}

	/**
	 * Valida bigdecimal.
	 *
	 * @param conteudo
	 * @return {@link BigDecimal}
	 */
	public static BigDecimal isBigDecimalValid(String conteudo) {
		try {
			return new BigDecimal(conteudo.trim().replace(",", "."));
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public static boolean equals(Integer obj1, Integer obj2){
		if (obj1 == null && obj2 == null) {
			return true;
		}

		if (obj1 == null || obj2 == null) {
			return false;
		}

		return obj1.equals(obj2);
	}

	
	public static List<Integer> converterListaBigDecimalParaInteger(List<BigDecimal> entrada) {
		List<Integer> result = new ArrayList<>();
		
		if (entrada != null && !entrada.isEmpty()) {
			entrada.forEach(item -> result.add(item.setScale(0).intValueExact()));
		}
		
		return result;
	}
	
	public static Boolean isMaiorOuIgualQue(BigDecimal maior, BigDecimal menor) {
		if (maior == null || menor == null) {
			return false;
		}
		
		return maior.compareTo(menor) == 0 || maior.compareTo(menor) >= 1;
	}

}
