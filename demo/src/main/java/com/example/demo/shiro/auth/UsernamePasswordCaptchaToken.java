package com.example.demo.shiro.auth;

import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * 继承UsernamePasswordToken
 * @author dongyan
 *
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -6397007182876785642L;

	private String captcha;
	
	public UsernamePasswordCaptchaToken() {
		super();
	}

	public UsernamePasswordCaptchaToken(String username, String password,
			boolean rememberMe, String captcha) {
		super(username, password, rememberMe);
		this.setCaptcha(captcha);
	}
	
	public UsernamePasswordCaptchaToken(String username, char[] password,
			boolean rememberMe, String captcha) {
		super(username, password, rememberMe);
		this.setCaptcha(captcha);
	}
	
	public UsernamePasswordCaptchaToken(String username, String password, String captcha) {
		super(username, password);
		this.setCaptcha(captcha);
	}
	
	public UsernamePasswordCaptchaToken(String username, char[] password, String captcha) {
		super(username, password);
		this.setCaptcha(captcha);
	}
	
	public UsernamePasswordCaptchaToken(String username, String password,
			boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.setCaptcha(captcha);
	}
	
	public UsernamePasswordCaptchaToken(String username, char[] password,
			boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.setCaptcha(captcha);
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	
}
