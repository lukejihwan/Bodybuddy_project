package com.edu.bodybuddy.exception;

public class AskException extends RuntimeException{
	public AskException(String msg) {
		super(msg);
	}
	public AskException(String msg, Throwable e) {
		super(msg, e);
	}
}
