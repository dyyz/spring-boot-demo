package com.example.demo.mongodb.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.mongodb.dao.IPublicDao;

@Repository
public class PublicDaoImpl<T> implements IPublicDao<T>{

	private @Autowired MongoTemplate mongoTemplate;
	
	@Override
	public void update(Query query, Update update, Class<?> t) {
		mongoTemplate.updateMulti(query, update, t);
	}
}
