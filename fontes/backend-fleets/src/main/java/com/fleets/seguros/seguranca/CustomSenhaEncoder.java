package com.fleets.seguros.seguranca;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fleets.seguros.util.HashUtil;

@Component
public class CustomSenhaEncoder implements PasswordEncoder {
	
	@Override
	public String encode(CharSequence senha) {
		String hash = HashUtil.getSecureHash(senha.toString());
		return hash;
	}

	@Override
	public boolean matches(CharSequence senha, String senhaCodificada) {
		String hash = HashUtil.getSecureHash(senha.toString());
		return hash.equals(senhaCodificada);
	}

}
