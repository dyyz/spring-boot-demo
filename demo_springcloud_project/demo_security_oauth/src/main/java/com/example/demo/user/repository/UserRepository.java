package com.example.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.entity.User;
import com.example.demo.user.entity.UserLogin;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserLogin(UserLogin userLogin);
}
