package com.example.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.entity.UserLogin;
import java.lang.String;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>{

	UserLogin findByUsername(String username);
}
