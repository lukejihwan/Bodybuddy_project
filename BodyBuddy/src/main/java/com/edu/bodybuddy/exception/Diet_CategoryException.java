package com.edu.bodybuddy.exception;

public class Diet_CategoryException extends RuntimeException{
	public Diet_CategoryException(String msg) {
		super(msg);
	}
	public Diet_CategoryException(String msg, Throwable e) {
		super(msg, e);
	}
}
