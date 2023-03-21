package com.edu.bodybuddy.exception;

public class QnaBoardException extends RuntimeException{
	public QnaBoardException(String msg) {
		super(msg);
	}
	public QnaBoardException(String msg, Throwable e) {
		super(msg, e);
	}
}
