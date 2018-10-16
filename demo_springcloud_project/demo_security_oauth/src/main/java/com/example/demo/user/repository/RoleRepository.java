package com.example.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.entity.Role;
import java.lang.String;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
