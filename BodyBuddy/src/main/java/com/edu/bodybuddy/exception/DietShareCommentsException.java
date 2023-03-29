package com.edu.bodybuddy.exception;

public class DietShareCommentsException extends RuntimeException{
	public DietShareCommentsException(String msg) {
		super(msg);
	}
	public DietShareCommentsException(String msg, Throwable e) {
		super(msg, e);
	}
}
