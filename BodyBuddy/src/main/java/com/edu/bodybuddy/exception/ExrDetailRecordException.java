package com.edu.bodybuddy.exception;

public class ExrDetailRecordException extends RuntimeException{
	public ExrDetailRecordException(String msg) {
		super(msg);
	}
	public ExrDetailRecordException(String msg, Throwable e) {
		super(msg, e);
	}
}
