package com.example.demo.shiro.service.impl;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.shiro.dao.UserInfoDao;
import com.example.demo.shiro.entity.UserInfo;
import com.example.demo.shiro.service.UserInfoService;

import java.util.List;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private final static Logger LOG = LoggerFactory.getLogger(UserInfoService.class);
	
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
    	LOG.info("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
    
    @Override
    public UserInfo addUser(UserInfo userInfo) {
    	LOG.info("UserInfoServiceImpl.addUser()");
    	String password = userInfo.getPassword();
    	String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
    	userInfo.setSalt(salt);
    	password = new SimpleHash("MD5", password, ByteSource.Util.bytes(userInfo.getCredentialsSalt()), 2).toHex();
    	userInfo.setPassword(password);
    	return userInfoDao.save(userInfo);
    }
    
    @Override
    public List<UserInfo> findAllUser() {
    	LOG.info("UserInfoServiceImpl.findAllUser()");
    	return (List<UserInfo>) userInfoDao.findAll();
    }
}