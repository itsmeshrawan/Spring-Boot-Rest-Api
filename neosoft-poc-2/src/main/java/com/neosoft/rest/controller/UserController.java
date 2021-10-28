package com.neosoft.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@PostMapping("/users")
	public String userRegistration() {
		return "Users api works";
	}
}
