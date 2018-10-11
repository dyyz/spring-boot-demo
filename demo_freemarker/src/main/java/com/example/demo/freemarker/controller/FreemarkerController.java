package com.example.demo.freemarker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.freemarker.entity.UserEntity;
import com.example.demo.freemarker.repository.UserRepository;

@Controller
@RequestMapping("/fm")
public class FreemarkerController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("name", "Joe");
		model.addAttribute("sex", "female");
		List<UserEntity> users = userRepository.findAll();
		model.addAttribute("users", users);
		
		return "/index";
	}
}
