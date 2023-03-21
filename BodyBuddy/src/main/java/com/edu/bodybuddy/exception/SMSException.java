package com.edu.bodybuddy.exception;

public class SMSException extends RuntimeException{
	public SMSException(String msg) {
		super(msg);
	}
	public SMSException(String msg, Throwable e) {
		super(msg, e);
	}
}
