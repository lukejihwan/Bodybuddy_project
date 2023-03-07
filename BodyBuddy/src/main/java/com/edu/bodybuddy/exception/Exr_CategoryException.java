package com.edu.bodybuddy.exception;

public class Exr_CategoryException extends RuntimeException{
	
	public Exr_CategoryException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public Exr_CategoryException(String msg) {
		super(msg);
	}

}
