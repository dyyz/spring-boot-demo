package com.example.demo.redis.service;

import org.springframework.cache.annotation.Cacheable;

import com.example.demo.shiro.entity.UserInfo;

public interface RedisService {

	@Cacheable(value = "users", key = "'user_' + #id")
	UserInfo getUser(Integer id);
}