package com.example.demo.hello.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceHystrix implements EurekaClientFeign {

	@Override
	public String sayHelloFromClientEureka(String name) {
		return "feedback";
	}

}
