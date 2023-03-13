package com.edu.bodybuddy.exception;

public class AddressException extends RuntimeException{
	public AddressException(String msg) {
		super(msg);
	}
	public AddressException(String msg, Throwable e) {
		super(msg, e);
	}
}
