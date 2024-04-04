package com.programming.siva.SpringSecDemo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
	
	public String extractUsername(String token)	{
		
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)	{
		
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String generateToken(UserDetails userDetails)	{
		
		return generateToken(new HashMap<>(), userDetails);
	}
	
	public String generateToken(
			Map<String, Object> extraClaims,
			UserDetails userDetails)	{
			
		return Jwts
				.builder()
				.claims(extraClaims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey())
				.compact();			
		
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails)	{
		
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token)	{
		
		return extractExpirationDate(token).before(new Date());
	}
	
	private Date extractExpirationDate(String token)	{
		
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token)	{
		
		return Jwts
				.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	private SecretKey getSignInKey() {
		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}
	
}

