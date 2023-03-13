package com.edu.bodybuddy.exception;

public class AskimgException extends RuntimeException{
	public AskimgException(String msg) {
		super(msg);
	}
	public AskimgException(String msg, Throwable e) {
		super(msg, e);
	}
}
