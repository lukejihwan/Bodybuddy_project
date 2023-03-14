package com.edu.bodybuddy.exception;

public class ExrRoutineException extends RuntimeException{
	public ExrRoutineException(String msg) {
		super(msg);
	}
	public ExrRoutineException(String msg, Throwable e) {
		super(msg, e);
	}
}
