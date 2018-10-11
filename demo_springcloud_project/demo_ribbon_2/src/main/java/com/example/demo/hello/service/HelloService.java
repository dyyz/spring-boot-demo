package com.example.demo.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String hiService(String name) {
        return restTemplate.getForObject("http://demo-client/hi?name=" + name, String.class);
    }
    
    @HystrixCommand(fallbackMethod = "error")
    public String helloService(String name) {
    	return restTemplate.getForObject("http://demo-client/hello?name=" + name, String.class);
    }
    
    public String error(String he) {
    	return "Ribbon熔断错误";
    }

}