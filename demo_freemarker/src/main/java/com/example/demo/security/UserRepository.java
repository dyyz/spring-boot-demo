package com.example.demo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.security.entity.User;

@Repository("securityUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}