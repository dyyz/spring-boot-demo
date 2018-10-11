package com.example.demo.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
/**
 * 自定义失败处理器
 *
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private Logger LOG = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		LOG.info(exception.getMessage() + "======登录失败=======");

//		response.setHeader("content-type", "text/html;charset=UTF-8");
//		response.getWriter().println("<script>alert('" + exception.getMessage() + "');</script>");
		response.sendRedirect("/login");
	}

}
