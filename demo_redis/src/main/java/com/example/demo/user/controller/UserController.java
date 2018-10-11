package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;

@RestController
public class UserController {

	@Autowired UserService userService;
	
	@GetMapping("/getUser")
	public User getUser() {
		return userService.getUser(1L);
	}
}
