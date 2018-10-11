package com.example.demo.shiro.service;

public interface UserLoginService {

	boolean validatCodeNumber(String codeNumber, String code);

	boolean validatLogin(String username, String password, String captcha, StringBuilder msg);

}
