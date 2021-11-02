package com.neosoft.rest.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

	@Bean
	public AuditorAware<String> auditorProvider() {
		
		/*
        	if we are using spring security, we can get the currently logged username with following code segment.
        	SecurityContextHolder.getContext().getAuthentication().getName()
       */
		return () -> Optional.ofNullable("Admin");
	}
}
