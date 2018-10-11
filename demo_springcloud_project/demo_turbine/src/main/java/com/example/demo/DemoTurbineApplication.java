package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringCloudApplication
@EnableTurbine
@EnableHystrixDashboard
public class DemoTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTurbineApplication.class, args);
	}
}
