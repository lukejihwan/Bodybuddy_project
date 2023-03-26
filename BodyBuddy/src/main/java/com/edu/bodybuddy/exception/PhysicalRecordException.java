package com.edu.bodybuddy.exception;

public class PhysicalRecordException extends RuntimeException{
	public PhysicalRecordException(String msg) {
		super(msg);
	}
	public PhysicalRecordException(String msg, Throwable e) {
		super(msg, e);
	}
}
