package com.edu.bodybuddy.exception;

public class ExrTipException extends RuntimeException{
	public ExrTipException(String msg) {
		super(msg);
	}
	public ExrTipException(String msg, Throwable e) {
		super(msg, e);
	}	
}
