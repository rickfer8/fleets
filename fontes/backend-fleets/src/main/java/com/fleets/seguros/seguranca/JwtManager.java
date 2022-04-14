package com.fleets.seguros.seguranca;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fleets.seguros.constante.Constante;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {
	
	public String createToken(String email, List<String> roles) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, Constante.JWT_EXP_DAYS);
		
		String jwt = Jwts.builder()
						 .setSubject(email)
						 .setExpiration(calendar.getTime())
						 .claim(Constante.JWT_ROLE_KEY, roles)
						 .signWith(SignatureAlgorithm.HS512, Constante.API_KEY.getBytes())
						 .compact();
		
		return jwt;						 
	}

}
