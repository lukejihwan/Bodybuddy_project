package com.edu.bodybuddy.exception;

public class Ask_imgException extends RuntimeException{
	public Ask_imgException(String msg) {
		super(msg);
	}
	public Ask_imgException(String msg, Throwable e) {
		super(msg, e);
	}
}
