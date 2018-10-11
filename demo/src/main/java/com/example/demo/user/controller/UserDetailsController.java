package com.example.demo.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.entity.UserDetails;
import com.example.demo.user.service.UserDetailsService;

@Controller
@RequestMapping("/user")
public class UserDetailsController {

	@Autowired
	UserDetailsService userdtailsService;
	
	@GetMapping("/update/{id}")
	public String userAdd(@PathVariable("id")Integer userInfoId, Model model) {
		UserDetails userDetails = userdtailsService.findByUserInfoUid(userInfoId);
		model.addAttribute("userDetails", userDetails);
		return "user/create";
	}
	
	@PostMapping("/update/{uid}")
	public String userUpdate(@Valid UserDetails userDetails, BindingResult bindingResult, 
			@PathVariable("uid")Integer userInfoId, 
			Model model) {
		if(bindingResult.hasErrors()) {
			userDetails.setUserInfo(userdtailsService.findByUserInfoUid(userInfoId).getUserInfo());
			model.addAttribute("userDetails", userDetails);
			System.out.println("userInfo.getUserName: " + userDetails.getUserInfo().getUsername());
			System.out.println(bindingResult.getFieldErrors().get(0).getDefaultMessage());
			System.out.println(bindingResult.getErrorCount());
		}
		return "user/create";
	}
	
}
