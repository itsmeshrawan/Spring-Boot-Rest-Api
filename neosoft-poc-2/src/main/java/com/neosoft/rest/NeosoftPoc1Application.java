package com.neosoft.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class NeosoftPoc1Application {

	public static void main(String[] args) {
		SpringApplication.run(NeosoftPoc1Application.class, args);
	}

	// Configuration for Swagger
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30).select()
				.apis(RequestHandlerSelectors.basePackage("com.neosoft.rest.controller")).build();
	}
}
