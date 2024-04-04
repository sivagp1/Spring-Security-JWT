package com.programming.siva.SpringSecDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.programming.siva.SpringSecDemo.service.UserDetailsImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig	{
	
//	@Autowired
//	private UserDetailsImpl userDetailsImpl;
	
//	@Autowired
//	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception	{
		 
		return http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/api/auth/**").permitAll()
		            .anyRequest().authenticated()
		        )
			.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			//.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration ) throws Exception	{
		
		return authenticationConfiguration.getAuthenticationManager();	
	}
	
//	@Bean
//	AuthenticationProvider authenticationProvider()	{
//		
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsImpl);
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}
	
	@Bean
	PasswordEncoder passwordEncoder()	{
		
		return new BCryptPasswordEncoder();
	}
}
