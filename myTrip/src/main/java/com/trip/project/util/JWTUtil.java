package com.trip.project.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class JWTUtil {
	
	public static String getUserName(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token)
				.getBody().get("userName",String.class);
	}
	
	public static boolean isExpired(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token)
				.getBody().getExpiration().before(new Date());
	}
	public static String createJwt(String userName, String secretKey,Long expiredMs) {	
		
		
		Claims claims = Jwts.claims();
		claims.put("userName",userName);
		return Jwts.builder()
				.setHeaderParam("type","jwt")
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ expiredMs))
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
				.compact();
		
	}
}
