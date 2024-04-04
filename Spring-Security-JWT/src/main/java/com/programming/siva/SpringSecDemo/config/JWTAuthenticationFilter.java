package com.programming.siva.SpringSecDemo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.programming.siva.SpringSecDemo.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTService jwtService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader=request.getHeader("Authorization");
		final String jwt; 
		final String username;
		if(authHeader == null || !authHeader.startsWith("Bearer"))	{
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt  = authHeader.substring(7);
		username = jwtService.extractUsername(jwt);
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)	{
			
		}
		
	}

}
