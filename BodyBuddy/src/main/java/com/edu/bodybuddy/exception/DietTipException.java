package com.edu.bodybuddy.exception;

public class DietTipException extends RuntimeException{
	public DietTipException(String msg) {
		super(msg);
	}
	public DietTipException(String msg, Throwable e) {
		super(msg, e);
	}
}
