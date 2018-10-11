package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class DemoAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAdminServerApplication.class, args);
	}
	
	@Configuration
	public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	    	http.formLogin().loginPage("/login")
	        	.loginProcessingUrl("/login")
	        	.permitAll();
	        http.logout().logoutUrl("/logout");
	        http.csrf().disable();
	        http.authorizeRequests()
	        .antMatchers("/login.html", "/**/**.css", "/img/**", "/third-party/**")
	        	.permitAll();
	        http.authorizeRequests()
	        	.antMatchers("/**").authenticated();
	        http.httpBasic();
	    }
	}
}
