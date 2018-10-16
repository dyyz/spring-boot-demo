package com.example.demo.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.user.entity.UserLogin;

public interface UserLoginService {

	void registryUser(UserLogin user, PasswordEncoder passwordEncoder);

}
