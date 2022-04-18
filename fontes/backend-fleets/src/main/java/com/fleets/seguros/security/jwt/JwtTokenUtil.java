package com.fleets.seguros.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String USERNAME = "sub";
	private static final String CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String getUserNameFromToken(String token) {
		String userName;
		try {
			final Claims claims = getClaimsFromToken(token);
			userName = claims.getSubject();
		} catch (Exception e) {
			userName = null;
		}
		return userName;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(USERNAME, userDetails.getUsername());
		claims.put(CREATED, new Date());

		return doGenerateToken(claims);
	}

	private String doGenerateToken(Map<String, Object> claims) {
		final Date createdDate = (Date) claims.get(CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
		return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean canTokenBenRefreshed(String token) {
		return !isTokenExpired(token);
	}
	
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CREATED, new Date());
			refreshedToken = doGenerateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		
		return refreshedToken;	
	}
	

	public Boolean validateToken(String token, UserDetails userDetails) {
		JwtUser jwtUser = (JwtUser) userDetails;
		final String userName = getUserNameFromToken(token);
		return (userName.equals(jwtUser.getUsername()) && !isTokenExpired(token));
	}
}
