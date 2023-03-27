package com.edu.bodybuddy.exception;

public class ExrTodayException extends RuntimeException{
	public ExrTodayException(String msg) {
		super(msg);
	}
	public ExrTodayException(String msg, Throwable e) {
		super(msg, e);
	}
}
