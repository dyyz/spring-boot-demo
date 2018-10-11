package com.example.demo.security.controller;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.security.UserLoginService;
import com.example.demo.user.entity.Role;
import com.example.demo.user.entity.UserLogin;
import com.example.demo.user.repository.UserLoginRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class LoginController {
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @RequestMapping({"/index", "/"})
    public String index(HttpServletRequest request) {
        System.out.println("登录成功");
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
    	return "/user/login";
    }
    
    @RequestMapping("/personal_center")
//    @Secured(value = {"ROLE_ADMIN", "ROLE_LEARNER"})	// 任一权限，前缀不能省略，搭配的@EnableGlobalMethodSecurity(securedEnabled = true)
    
//    搭配的是@EnableGlobalMethodSecurity(securedEnabled = true)
//    @RolesAllowed(value = {"ROLE_ADMIN", "LEARNER"})	// 任一权限，前缀ROLE_可省略 
//    @DenyAll	// 拒绝所有访问
//    @PermitAll	// 运行所有访问
    
//    搭配的是@EnableGlobalMethodSecurity(prePostEnabled = true)
    @PreAuthorize("hasRole('ADMIN')")	//在方法调用前，基于表达式计算结果来限制方法访问
//    @PostAuthorize: 允许方法调用，但是如果表达式结果为fasle则抛出异常
    //returnObject可以获取返回对象user，判断user属性username是否和访问该方法的用户对象的用户名一样。不一样则抛出异常
//    @PostAuthorize("returnObject.user.username==principal.username")
//    public User getUser(int userId){
//       //允许进入
//    ...
//        return user;    
//    }
//    @PostFilter :允许方法调用，但必须按表达式过滤方法结果。
    //将结果过滤，即选出性别为男的用户
//    @PostFilter("returnObject.user.sex=='男' ")
//    public List<User> getUserList(){
//       //允许进入
//    ...
//        return user;    
//    }
//    @PreFilter:允许方法调用，但必须在进入方法前过滤输入值
    public String login(HttpServletRequest request) {
        return "/user/personal_center";
    }
    
    @ResponseBody
    @PostMapping("/registry")
    public String registry(UserLogin user) {
    	userLoginService.registryUser(user, passwordEncoder);
        return "注册成功!!!";
    }
    
    @GetMapping("/registry")
    public String registry() {
    	return "/user/registry";
    }
    
    @GetMapping("/error")
    public String error(Exception exception, Model model) {
    	model.addAttribute("message", exception.getMessage());
    	return "/error";
    }
}
