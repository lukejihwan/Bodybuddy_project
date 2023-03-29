package com.edu.bodybuddy.exception;

public class DailyWalkException extends RuntimeException{
	public DailyWalkException(String msg) {
		super(msg);
	}
	public DailyWalkException(String msg, Throwable e) {
		super(msg, e);
	}
}
