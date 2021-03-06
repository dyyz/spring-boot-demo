package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestConfigController {

	@Value("${foo}")
	String foo;
	
	@RequestMapping(value = "/foo", method = RequestMethod.GET)
	public String hi() {
		return foo;
	}
}
