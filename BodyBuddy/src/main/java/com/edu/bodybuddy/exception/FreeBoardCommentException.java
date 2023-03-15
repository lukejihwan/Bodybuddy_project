package com.edu.bodybuddy.exception;

public class FreeBoardCommentException extends RuntimeException{
	public FreeBoardCommentException(String msg) {
		super(msg);
	}
	public FreeBoardCommentException(String msg, Throwable e) {
		super(msg, e);
	}
}
