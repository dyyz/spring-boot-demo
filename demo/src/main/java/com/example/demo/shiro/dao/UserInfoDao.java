package com.example.demo.shiro.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.shiro.entity.UserInfo;

import org.springframework.data.repository.CrudRepository;

public interface UserInfoDao extends CrudRepository<UserInfo, Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
    
    UserInfo findByUid(Integer uid);
}
