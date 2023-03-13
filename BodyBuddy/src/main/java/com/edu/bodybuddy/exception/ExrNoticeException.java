package com.edu.bodybuddy.exception;

public class ExrNoticeException extends RuntimeException{
	public ExrNoticeException(String msg) {
		super(msg);
	}
	public ExrNoticeException(String msg, Throwable e) {
		super(msg, e);
	}	
}
