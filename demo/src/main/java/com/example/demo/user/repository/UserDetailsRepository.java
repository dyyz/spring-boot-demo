package com.example.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.user.entity.UserDetails;

public interface UserDetailsRepository extends 
	JpaRepository<UserDetails, Long>, PagingAndSortingRepository<UserDetails, Long> {

	UserDetails findByUserInfoUid(Integer userInfoId);
	
}
