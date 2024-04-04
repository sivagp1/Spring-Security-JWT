package com.programming.siva.SpringSecDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.programming.siva.SpringSecDemo.dto.AuthenticationResponse;
import com.programming.siva.SpringSecDemo.dto.LoginDto;
import com.programming.siva.SpringSecDemo.dto.RegisterDto;
import com.programming.siva.SpringSecDemo.model.User;
import com.programming.siva.SpringSecDemo.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTService jwtService;
	
	public AuthenticationResponse register(RegisterDto request) {
	    User user = mapReqtoUser(request);
	    userRepository.save(user);
	    var jwtToken = jwtService.generateToken(user);
	    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
	    authenticationResponse.setAccessToken(jwtToken);
	    return authenticationResponse;
	  }
	
	private User mapReqtoUser(RegisterDto request)	{
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setRole(request.getRole());
		return user;
	}
	
	public AuthenticationResponse authenticate(LoginDto request) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getUsername(),
	            request.getPassword()
	        )
	    );
	    User user = userRepository.findByUsername(request.getUsername())
	        .orElseThrow();
	    var jwtToken = jwtService.generateToken(user);
	    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
	    authenticationResponse.setAccessToken(jwtToken);
	    return authenticationResponse;
	  }

}
