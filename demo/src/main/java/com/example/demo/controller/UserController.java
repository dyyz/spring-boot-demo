package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/addUser")
	private Integer addUser() {
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setName("department1");
		
		UserEntity userEntity = new UserEntity();
		userEntity.setName("Sam");
		userEntity.setDepartmentEntity(null);
		
		return userService.addUser(userEntity);
	}
	
	@GetMapping("/findAll")
	private List<UserEntity> findAll() {
		
		return userService.findAllSortBy(Direction.DESC, "id");
	}
	
	@GetMapping("/getAll")
	private List<UserEntity> getAll() {
		
		return userService.getAllUser(1, 4);
	}

	@GetMapping("/query")
	public List<UserEntity> queryForUser(){
		Pageable pageable = PageRequest.of(1, 2);
		
		return userService.queryUserById(2, pageable).getContent();
	}
}
