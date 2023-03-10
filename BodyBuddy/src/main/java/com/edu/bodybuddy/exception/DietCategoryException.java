package com.edu.bodybuddy.exception;

public class DietCategoryException extends RuntimeException{
	public DietCategoryException(String msg) {
		super(msg);
	}
	public DietCategoryException(String msg, Throwable e) {
		super(msg, e);
	}
}
