package com.fleets.seguros.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
	
	public static String getSecureHash(String valor) {
		String hash = DigestUtils.sha256Hex(valor);
		return hash;
	}

}
