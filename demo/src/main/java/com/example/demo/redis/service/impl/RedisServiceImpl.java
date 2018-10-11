package com.example.demo.redis.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.redis.service.RedisService;
import com.example.demo.shiro.entity.UserInfo;

@Service
public class RedisServiceImpl implements RedisService {

	@Override
	public UserInfo getUser(Integer id) {
		System.out.println("RedisService.getUser(Integer id).....");
		UserInfo userInfo = new UserInfo();
		userInfo.setUid(id);
		userInfo.setName("香菇");
		userInfo.setPassword("password");
		userInfo.setUsername("南瘦");
		return userInfo;
	}
	
}
