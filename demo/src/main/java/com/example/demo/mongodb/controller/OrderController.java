package com.example.demo.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mongodb.Order;
import com.example.demo.mongodb.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired OrderService orderService;
	
	@PostMapping
	public String saveOrder(Order order) {
		orderService.save(order);
		return order.toString();
	}
	
	@GetMapping("/{id}")
	public String findOrder(@PathVariable("id")String id) {
		return orderService.findOne(id).toString();
	}
	
	@GetMapping
	public String findAll() {
		return orderService.findAll().toString();
	}
	
	@PutMapping("/{id}")
	public String updateName(@PathVariable("id") String id, @RequestParam("name") String name) {
		return Boolean.toString(orderService.updateNameById(id, name));
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") String id) {
		orderService.deleteById(id);
		return "删除成功！！！";
	}
}
