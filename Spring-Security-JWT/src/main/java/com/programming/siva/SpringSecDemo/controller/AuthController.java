package com.programming.siva.SpringSecDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programming.siva.SpringSecDemo.dto.AuthenticationResponse;
import com.programming.siva.SpringSecDemo.dto.LoginDto;
import com.programming.siva.SpringSecDemo.dto.RegisterDto;
import com.programming.siva.SpringSecDemo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/register")
	  public ResponseEntity<AuthenticationResponse> register(
	      @RequestBody RegisterDto request 
	  ) {
	    return ResponseEntity.ok(authService.register(request));
	  }
	
	
	  @PostMapping("/authenticate")
	  public ResponseEntity<AuthenticationResponse> authenticate(
	      @RequestBody LoginDto request
	  ) {
	    return ResponseEntity.ok(authService.authenticate(request));
	  }
	
	
}
