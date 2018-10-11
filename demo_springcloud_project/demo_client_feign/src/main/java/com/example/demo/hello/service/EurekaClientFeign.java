package com.example.demo.hello.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "demo-client", fallback = UserServiceHystrix.class)
public interface EurekaClientFeign {
	
	@RequestMapping(value = "/hi")
	String sayHelloFromClientEureka(@RequestParam(value = "name") String name);
}
