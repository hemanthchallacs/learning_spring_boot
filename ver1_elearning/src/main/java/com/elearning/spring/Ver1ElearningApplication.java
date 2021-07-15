package com.elearning.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@SpringBootApplication
public class Ver1ElearningApplication {

	public static void main(String[] args) {
		
		// templateResolver.setTemplateMode(TemplateMode.HTML5);   DEPRECATED!!
		            // OK for 2.0!
		SpringApplication.run(Ver1ElearningApplication.class, args);
	}
	
	
}
