package com.edu.bodybuddy.exception;

public class PasswordException extends RuntimeException{
	public PasswordException(String msg) {
		super(msg);
	}
	public PasswordException(String msg, Throwable e) {
		super(msg, e);
	}
}
