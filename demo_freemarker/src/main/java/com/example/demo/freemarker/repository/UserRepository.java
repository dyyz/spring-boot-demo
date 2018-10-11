package com.example.demo.freemarker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.freemarker.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>, PagingAndSortingRepository<UserEntity, Integer> {

}
