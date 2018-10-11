package com.example.demo.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.hello.service.HelloService;

@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;
    
    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }

    @Autowired
    private LoadBalancerClient loadBlancerClient;
    
    @RequestMapping(value = "/testRibbon")
    public String testRibbon(@RequestParam String name){
        ServiceInstance serviceInstance = loadBlancerClient.choose("demo-client");
        return serviceInstance.getHost() + ":" + serviceInstance.getPort();
    }
    
    @GetMapping(value = "/hello")
    public String hello(String name) {
    	return helloService.helloService(name);
    }

}