package com.edu.bodybuddy.exception;

public class DietRecordException extends RuntimeException{
	public DietRecordException(String msg) {
		super(msg);
	}
	public DietRecordException(String msg, Throwable e) {
		super(msg, e);
	}
}
