package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface UserDao {

	long getQueryCount(StringBuilder sql, Map<String, Object> params);

	List getQueryResult(StringBuilder sql, Map<String, Object> params, Pageable page);

}
