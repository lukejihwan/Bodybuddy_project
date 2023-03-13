package com.edu.bodybuddy.exception;

public class FreeBoardException extends RuntimeException{
	public FreeBoardException(String msg) {
		super(msg);
	}
	public FreeBoardException(String msg, Throwable e) {
		super(msg, e);
	}
}
