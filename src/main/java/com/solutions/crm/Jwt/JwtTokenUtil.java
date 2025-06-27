package com.solutions.crm.Jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.solutions.crm.beans.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 Hr

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

	@Value("$app.jwt.secret")
	private String secretKey;

	public String generateAccessToken(Users users) {
		return Jwts.builder().setSubject(users.getId()+ "," + users.getUsername()).setIssuer("Water Softwares")
				.claim("roles", users.getRoles().toString()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

			return true;

		} catch (ExpiredJwtException e) {
			LOGGER.error("JWT Expired", e);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Token is Null,Empty or has only whitespaces", e);
		} catch (MalformedJwtException e) {
			LOGGER.error("JWT is Invalid", e);
		} catch (UnsupportedJwtException e) {
			LOGGER.error("JWT is Not Supported", e);
		} catch (SignatureException e) {
			LOGGER.error("Signature Validation Failed", e);
		}
		return false;
	}

	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}

	public Claims parseClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

	}
}
