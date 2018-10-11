package com.example.demo.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.user.entity.UserLogin;

public interface UserLoginService {

	void registryUser(UserLogin user, PasswordEncoder passwordEncoder);

}
