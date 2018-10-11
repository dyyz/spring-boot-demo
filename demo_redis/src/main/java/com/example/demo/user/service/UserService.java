package com.example.demo.user.service;

import org.springframework.cache.annotation.Cacheable;

import com.example.demo.user.entity.User;

public interface UserService {

	@Cacheable(value = "users", key = "'user_' + #id")
	public User getUser(Long id);
}
