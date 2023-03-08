package com.edu.bodybuddy.exception;

public class ExrCategoryException extends RuntimeException{
	public ExrCategoryException(String msg, Throwable e) {
		super(msg, e);
	}
	public ExrCategoryException(String msg) {
		super(msg);
	}
}
