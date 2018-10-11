package com.example.demo.user.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.entity.User;
import com.example.demo.user.entity.UserLogin;
import com.example.demo.user.repository.UserLoginRepository;
import com.example.demo.user.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	private @Autowired UserRepository userRepository;
	private @Autowired UserLoginRepository userLoginRepository;
	
	@GetMapping("/info")
	public String modifyUser(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserLogin userLogin = userLoginRepository.findByUsername(userDetails.getUsername());
		User exisitUser = userRepository.findByUserLogin(userLogin);
		User user = exisitUser == null ? new User(userLogin) : exisitUser;
		model.addAttribute("user", user);
		return "user/personal_center";
	}
	
	@PostMapping("/modify")
	@Transactional
	public String modifyUserInfo(@Valid User user, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("user", user);
			return "/user/personal_center";
		}
		userRepository.save(user);
		return "redirect:/index";
	}
}
