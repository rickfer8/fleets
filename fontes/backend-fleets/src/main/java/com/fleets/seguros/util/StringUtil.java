package com.fleets.seguros.util;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private StringUtil() { }

	/**
	 * Remove acentos da string informada.
	 *
	 * @param str
	 * @return {@link String}
	 */
	public static String removerAcentos(String str) {
		return str != null ? Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "") : str;
	}
	
	/**
	 * Verifica se uma string é um regex válido.
	 *
	 * @param conteudo
	 * @param pattern
	 * @return {@link boolean}
	 */
	public static boolean isFormatValidRegex(String conteudo, String pattern) {
		Pattern regex = Pattern.compile(pattern);
		Matcher regexMatcher = regex.matcher(conteudo.trim());

		return regexMatcher.matches();
	}

	public static boolean equals(String str1, String str2){
		if (str1 == null && str2 == null) {
			return true;
		}

		if (str1 == null || str2 == null) {
			return false;
		}

		return str1.equals(str2);
	}

	/**
	 * Remove todos os espaços em branco de uma string
	 * @param str1
	 * @return {@String nova String modificada}
	 */
	public static String removeAllSpaces(String str1) {
		return str1 != null ? str1.replaceAll("\\s+","") : null;
	}

	/**
	 * Remove todos os espaços consecutivos de uma string
	 * @param str1
	 * @return {@String nova String modificada}
	 */
	public static String removeConsecutiveSpaces(String str1) {
		return str1 != null ? str1.replaceAll("\\s{2,}", " ").trim() : null;
	}
}
