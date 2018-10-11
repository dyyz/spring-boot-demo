package com.example.demo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component  //必须有
@Order(1)
public class Runner2 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Runner 2 start to initlization...");
	}

}
