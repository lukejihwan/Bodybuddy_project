package com.edu.bodybuddy.exception;

public class DietTipCommentsException extends RuntimeException{
	public DietTipCommentsException(String msg) {
		super(msg);
	}
	public DietTipCommentsException(String msg, Throwable e) {
		super(msg, e);
	}
}
