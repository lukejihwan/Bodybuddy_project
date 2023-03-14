package com.edu.bodybuddy.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginException extends AuthenticationException{

	public LoginException(String msg) {
		super(msg);
	}
	
	public LoginException(String msg, Throwable t) {
		super(msg, t);
	}

}
