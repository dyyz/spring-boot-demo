package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.example.demo.entity.UserEntity;

public interface UserService {

	public Integer addUser(UserEntity user);

	List<UserEntity> findAllSortBy(Direction direction, String param);

	List<UserEntity> getAllUser(int page, int size);

	Page<UserEntity> queryUserById(Integer departmentId, Pageable page);
}
