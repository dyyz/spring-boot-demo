package com.example.demo.user.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.shiro.dao.UserInfoDao;
import com.example.demo.shiro.entity.UserInfo;
import com.example.demo.user.entity.UserDetails;
import com.example.demo.user.repository.UserDetailsRepository;
import com.example.demo.user.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	UserInfoDao userInfoDao;
	
	@Override
	public UserDetails findByUserInfoUid(Integer uid) {
		UserInfo userInfo = Optional.ofNullable(userInfoDao.findByUid(uid)).orElse(new UserInfo());
		return Optional.ofNullable(userDetailsRepository.findByUserInfoUid(uid)).orElse(new UserDetails(userInfo));
	}
	
}
