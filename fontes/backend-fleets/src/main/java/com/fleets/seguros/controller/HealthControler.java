package com.fleets.seguros.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleets.seguros.util.Response;

@RestController
public class HealthControler {	
	
	@GetMapping()
	public ResponseEntity<Response> isHealthS3(){		
		Response response = Response.builder().code(HttpStatus.OK.value()).message("UP").build();
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
	}

}
