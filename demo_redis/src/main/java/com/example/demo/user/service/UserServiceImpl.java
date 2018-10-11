package com.example.demo.user.service;

import org.springframework.stereotype.Service;

import com.example.demo.user.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User getUser(Long id) {
		System.out.println("RedisService.getUser(Integer id).....");
		User user = new User();
		user.setId(id);
		user.setName("Jams");
		user.setNickName("Jams Jeckson");
		user.setAge(22);
		return user;
	}

}
