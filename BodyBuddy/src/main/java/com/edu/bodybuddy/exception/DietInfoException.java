package com.edu.bodybuddy.exception;

public class DietInfoException extends RuntimeException{
	public DietInfoException(String msg) {
		super(msg);
	}
	public DietInfoException(String msg, Throwable e) {
		super(msg, e);
	}
}
