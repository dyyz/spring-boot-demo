package com.example.demo.shiro.service;

import java.util.List;

import com.example.demo.shiro.entity.UserInfo;

public interface UserInfoService {

	UserInfo findByUsername(String username);

	UserInfo addUser(UserInfo userInfo);

	List<UserInfo> findAllUser();

}
