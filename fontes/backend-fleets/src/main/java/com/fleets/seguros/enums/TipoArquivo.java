package com.fleets.seguros.enums;

import static java.util.Objects.isNull;

import com.fleets.seguros.util.EnumUtil;

public enum TipoArquivo implements EnumValue {

	XLS("XLS"),
	XLSX("XLSX");
	
	private String value;
	
	/**
	 * Instancia um novo tipo arquivo.
	 *
	 * @param value the value
	 */
	TipoArquivo(String value) {
		this.value = value;
	}
	
	@Override
	public String toValue() {
		return value;
	}
	
	/**
	 * From value.
	 *
	 * @param extensao the extensao
	 * @return {@link TipoArquivo}
	 */
	public static TipoArquivo fromValue(String extensao) {
		if (isNull(extensao)) {
			return null;
		}
		
		return EnumUtil.fromValue(TipoArquivo.class, extensao.toUpperCase().trim());
	}
}
