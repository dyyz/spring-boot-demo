package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.hello.service.HelloFeignService;

@RestController
public class HelloController {

	@Autowired
	private HelloFeignService helloFeignService;
	
	@GetMapping("/sayHi")
	public String sayHi(@RequestParam(defaultValue = "forezp", required = false) String name) {
		return helloFeignService.sayHelloFromClientEureka(name);
	}
}
