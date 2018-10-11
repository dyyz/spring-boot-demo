package com.example.demo.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.redis.service.RedisService;
import com.example.demo.service.UserService;
import com.example.demo.shiro.entity.UserInfo;

@RestController
public class RedisController {

	@Autowired
	private StringRedisTemplate template;
	
	@Autowired
	private RedisService redisService;
    
    @RequestMapping("/setValue")
    public String setValue(){
    	if(!template.hasKey("redisFromSTS")){
    		template.opsForValue().append("redisFromSTS", "Yes");
    		return "使用redis缓存保存数据成功";
    	}else{
    		template.delete("redisFromSTS");
    		return "key已存在";
    	}
    }
    
    @RequestMapping("/getValue")
    public String getValue(){
    	
    	if(!template.hasKey("redisFromSTS")){
    		return "key不存在，请先保存数据";
    	}else{
    		String shabao = template.opsForValue().get("redisFromSTS");//根据key获取缓存中的val 
    		return "获取到缓存中的数据：redisFromSTS="+shabao;
    	}
    }
    
    @RequestMapping("/redis/getUser")
    public UserInfo getUser(){
    	return redisService.getUser(1);
    }

}
