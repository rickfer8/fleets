package com.fleets.seguros.util;

import java.util.Optional;
import java.util.stream.Stream;

import com.fleets.seguros.enums.EnumValue;

/**
 * EnumUtil.
 */
public class EnumUtil {

	private EnumUtil() {}
	
	/**
	 * From value.
	 *
	 * @param <E> the element type
	 * @param enumType the enum type
	 * @param enumValue the enum value
	 * @return {@link E}
	 */
	public static <E extends EnumValue> E fromValue(Class<E> enumType, String enumValue) {
		final Optional<E> first = Stream.of(enumType.getEnumConstants())
			.filter(e -> e.toValue().equals(enumValue))
			.findFirst();
		
		return first.isPresent() ? first.get() : null;
	}
	 
}
