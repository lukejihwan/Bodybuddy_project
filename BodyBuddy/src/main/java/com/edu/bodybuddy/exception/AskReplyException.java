package com.edu.bodybuddy.exception;

public class AskReplyException extends RuntimeException{
	public AskReplyException(String msg) {
		super(msg);
	}
	public AskReplyException(String msg, Throwable e) {
		super(msg, e);
	}
}
