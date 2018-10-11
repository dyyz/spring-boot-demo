package com.example.demo.security.authentication;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义登录成功处理
 *
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private Logger LOG = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		LOG.info(authentication.getName() + " 在" + new Date() + " 时登录成功");
//		response.setContentType("application/json;charset=UTF-8");
//		response.getWriter().write(objectMapper.writeValueAsString(authentication));
		response.sendRedirect("/index");
	}

}
