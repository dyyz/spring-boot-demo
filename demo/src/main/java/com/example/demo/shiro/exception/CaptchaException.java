package com.example.demo.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = -4929780085416061064L;
	
	public CaptchaException() {
		super();
	}

	public CaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CaptchaException(String message) {
		super(message);
	}

	public CaptchaException(Throwable cause) {
		super(cause);
	}

}
