package com.example.demo.shiro.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.example.demo.shiro.auth.UsernamePasswordCaptchaToken;
import com.example.demo.shiro.exception.CaptchaException;
import com.example.demo.shiro.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	/**
	 * codeNumber: code in session
	 * checkcode: input code
	 */
	@Override
	public boolean validatCodeNumber(String codeNumber, String checkcode) {
		boolean isEqual = false;
		if(StringUtils.isEmpty(codeNumber)) {
    	} else if(StringUtils.isEmpty(checkcode)) {
    	} else if(!checkcode.trim().equalsIgnoreCase(codeNumber.trim())) {
    	} else {
    		isEqual = true;
    	}
		return isEqual;
	}
	
	@Override
	public boolean validatLogin(String username, String password, String captcha, StringBuilder msg) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(username, password, captcha);
    	boolean isLogin = true;
    	try {
    		subject.login(token);
    	} catch (UnknownAccountException ex) {
    		System.out.println("UnknownAccountException -- > 账号不存在：");
            msg.append("UnknownAccountException -- > 账号不存在：");
            isLogin = false;
    	} catch (IncorrectCredentialsException ex) {
    		System.out.println("IncorrectCredentialsException -- > 密码不正确：");
            msg.append("IncorrectCredentialsException -- > 密码不正确：");
            isLogin = false;
    	} catch (ExcessiveAttemptsException ex) {
    		 System.out.println("ExcessiveAttemptsException -- > 登录失败次数过多：");
             msg.append("ExcessiveAttemptsException -- > 登录失败次数过多：");
             isLogin = false;
    	} catch (CaptchaException ex) {
    		System.out.println("CaptchaException -- > 验证码错误：");
            msg.append("CaptchaException -- > 验证码错误：");
            isLogin = false;
    	} catch (Exception ex) {
    		System.out.println("else -- >" + ex);
    		msg.append("else -- >" + ex);
    		isLogin = false;
    	}
		return isLogin;
	}
}
