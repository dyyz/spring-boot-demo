package com.example.demo.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloFeignService {

	@Autowired
	EurekaClientFeign eurekaClientFeign;
	
	public String sayHelloFromClientEureka(String name) {
		return eurekaClientFeign.sayHelloFromClientEureka(name);
	}

}
