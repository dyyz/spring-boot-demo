package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

//@EnableDiscoveryClient
@EnableEurekaClient
@SpringCloudApplication
@EnableHystrix
@EnableHystrixDashboard // 单体熔断
public class DemoRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRibbonApplication.class, args);
	}
	
//	  @Bean
//	  public ServletRegistrationBean getServlet() {
//	    HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//	    ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//	    registrationBean.setLoadOnStartup(1);
//	    registrationBean.addUrlMappings("/hystrix.stream");
//	    registrationBean.setName("HystrixMetricsStreamServlet");
//	    return registrationBean;
//	  }
}
