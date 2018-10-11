package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
@EnableScheduling
//@ImportResource(value = "classpath:/config/kaptcha.xml") // 配置kaptcha验证码的第一种方式
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("The service to start.");
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("The service has started.");
	}
}
