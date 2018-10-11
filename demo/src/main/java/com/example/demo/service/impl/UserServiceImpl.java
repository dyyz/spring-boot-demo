package com.example.demo.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public Integer addUser(UserEntity user) {
		userRepository.save(user);
		Integer id = user.getId();
		user.setName(id + "_" + user.getName());
		userRepository.save(user);
		return id;
	}
	
	@Override
	public List<UserEntity> findAllSortBy(Direction direction, String param){
		Sort sort = new Sort(direction, param);
		return userRepository.findAll(sort);
	}
	
	/**
	 * 分页
	 */
	@Override
	public List<UserEntity> getAllUser(int page, int size){
		PageRequest pageable = PageRequest.of(page, size);
		Page<UserEntity> pageObject = userRepository.findAll(pageable);
		int totalPage = pageObject.getTotalPages();
		System.out.println("totalPage:  " + totalPage);
		long count = pageObject.getTotalElements();
		System.out.println("count:  " + count);
		
		return pageObject.getContent();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<UserEntity> queryUserById(Integer departmentId, Pageable page) {
		StringBuilder sql = new StringBuilder(" from UserEntity u where 1 = 1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if(departmentId != null) {
			sql.append(" and u.department.id = :departId");
			params.put("departId", departmentId);
		}
		
		long count = userDao.getQueryCount(sql, params);
		if(count == 0) {
			return new PageImpl<>(Collections.emptyList(), page, 0);
		}
		List users = userDao.getQueryResult(sql, params, page);
		Page<UserEntity> result = new PageImpl<>(users, page, count);
		return result;
	}
	
}
