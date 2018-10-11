package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoWebfluxApplication.class);
		app.setWebApplicationType(WebApplicationType.REACTIVE);
		app.run(args);
//		SpringApplication.run(DemoWebfluxApplication.class, args);
	}
}
