package com.edu.bodybuddy.exception;

public class ExrRecordException extends RuntimeException{
	public ExrRecordException(String msg) {
		super(msg);
	}
	public ExrRecordException(String msg, Throwable e) {
		super(msg, e);
	}
}
