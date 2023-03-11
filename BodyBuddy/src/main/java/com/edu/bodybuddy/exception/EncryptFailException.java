package com.edu.bodybuddy.exception;

public class EncryptFailException extends RuntimeException{
	public EncryptFailException(String msg) {
		super(msg);
	}
	public EncryptFailException(String msg, Throwable e) {
		super(msg, e);
	}
}
