package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/client1")
	public String test1() {
		return "Client1 方法返回值~~~~~~";
	}
}
