package com.example.demo.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.security.UserRepository;
import com.example.demo.security.entity.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    @Qualifier("securityUserRepository")
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ResponseBody
    @RequestMapping("/personal_center")
    public String login(HttpServletRequest request) {
        System.out.println("登录成功");
        return "登录成功!!!";
    }

    @ResponseBody
    @PostMapping("/registry")
    public String registry(User user) {
        userRepository.save(new User(user.getUsername(), 
        		passwordEncoder.encode(user.getPassword())));
        return "注册成功!!!";
    }
    
    @GetMapping("/registry")
    public String registry() {
    	return "/registry";
    }
}