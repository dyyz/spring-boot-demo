package com.example.demo.mongodb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.mongodb.Order;
import com.example.demo.mongodb.dao.IPublicDao;
import com.example.demo.mongodb.repository.OrderRepository;
import com.example.demo.mongodb.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired private OrderRepository orderRepository;
	@Autowired private IPublicDao<Order> publicDao;
	
	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}
	
	@Override
	public Optional<Order> findOne(String id) {
		return orderRepository.findById(id);
	}
	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
	@Override
	public boolean updateNameById(String id, String name) {
		Query query = new Query(Criteria.where("_id").is(id));
		Update update = Update.update("name", name);
		publicDao.update(query, update, Order.class);
		return true;
	}
	
	@Override
	public void deleteById(String id) {
		orderRepository.deleteById(id);
	}
}
