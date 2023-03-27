package com.edu.bodybuddy.exception;

public class DietShareException extends RuntimeException{
	public DietShareException(String msg) {
		super(msg);
	}
	public DietShareException(String msg, Throwable e) {
		super(msg, e);
	}
}
