package com.edu.bodybuddy.exception;

public class GpsDataException extends RuntimeException{
	public GpsDataException(String msg) {
		super(msg);
	}
	public GpsDataException(String msg, Throwable e) {
		super(msg, e);
	}
}
