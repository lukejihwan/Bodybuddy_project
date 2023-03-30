package com.edu.bodybuddy.exception;

public class InterestException extends RuntimeException{
	public InterestException(String msg) {
		super(msg);
	}
	public InterestException(String msg, Throwable e) {
		super(msg, e);
	}
}
