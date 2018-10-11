package com.example.demo.util;

import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.AuditorAware;

import com.example.demo.shiro.entity.UserInfo;

public class MyAuditorAware implements AuditorAware<UserInfo> {

	@Override
	public Optional<UserInfo> getCurrentAuditor() {
		
		Subject subject = SecurityUtils.getSubject();
		if(subject == null || !subject.isAuthenticated()) {
			return null;
		}
		
//		return (Optional<UserInfo>) SecurityUtils.getSubject().getPrincipal();
		return Optional.of((UserInfo)SecurityUtils.getSubject().getPrincipal());
	}

}
