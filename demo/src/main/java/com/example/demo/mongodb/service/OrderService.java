package com.example.demo.mongodb.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.mongodb.Order;

public interface OrderService {

	void save(Order order);

	Optional<Order> findOne(String id);

	List<Order> findAll();

	boolean updateNameById(String id, String name);

	void deleteById(String id);

}
