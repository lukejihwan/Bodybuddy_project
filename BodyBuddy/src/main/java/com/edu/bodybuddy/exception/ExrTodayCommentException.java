package com.edu.bodybuddy.exception;

public class ExrTodayCommentException extends RuntimeException{
	public ExrTodayCommentException(String msg) {
		super(msg);
	}
	public ExrTodayCommentException(String msg, Throwable e) {
		super(msg, e);
	}
}
