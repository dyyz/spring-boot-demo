package com.example.demo.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.mongodb.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
