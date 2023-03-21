package com.edu.bodybuddy.exception;

public class QnaBoardCommentException extends RuntimeException{
	public QnaBoardCommentException(String msg) {
		super(msg);
	}
	public QnaBoardCommentException(String msg, Throwable e) {
		super(msg, e);
	}
}
