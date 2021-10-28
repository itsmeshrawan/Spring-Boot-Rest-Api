package com.neosoft.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NeosoftPoc2Application {

	public static void main(String[] args) {
		SpringApplication.run(NeosoftPoc2Application.class, args);
	}
	
	@GetMapping("/")
	public String helloWorld() {
		return "Hello World!";
	}
}
