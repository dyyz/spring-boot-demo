package com.example.demo.user.service;

import com.example.demo.user.entity.UserDetails;

public interface UserDetailsService {

	UserDetails findByUserInfoUid(Integer uid);

}
