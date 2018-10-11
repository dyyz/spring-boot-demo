package com.example.demo.util;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MyWebConfigurer implements WebMvcConfigurer {
	
	/*@Bean
	SessionInterceptor sessionInterceptor() {
		return new SessionInterceptor();
	}*/
	 
	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
//		registry.addInterceptor(sessionInterceptor())
//        .addPathPatterns("/**")
//        .excludePathPatterns("/login","/permission/userInsert",
//                "/error","/tUser/insert","/gif/getGifCode");

	}
	
	/**
	 * 跨域访问配置
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addCorsMappings(registry);
	}
	
	/**
	 * 格式化
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addFormatters(registry);
//		registry.addConverter(new DateConverter());
		registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
	}
	
	/**
	 * URI到视图的映射
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addViewControllers(registry);
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addReturnValueHandlers(handlers);
	}
	
}
