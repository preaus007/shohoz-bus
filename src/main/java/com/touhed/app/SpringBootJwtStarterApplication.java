package com.touhed.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing( auditorAwareRef = "auditorAwareImpl" )
public class SpringBootJwtStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtStarterApplication.class, args);
	}

}
