package com.programming.siva.SpringSecDemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request")
public class DemoController {
	
	  @GetMapping
	  public ResponseEntity<String> register() {
	    return ResponseEntity.ok("Hello from secure endpoint");
	  }
	
	

}
