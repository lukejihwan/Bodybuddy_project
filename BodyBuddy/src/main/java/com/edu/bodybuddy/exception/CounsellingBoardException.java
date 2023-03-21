package com.edu.bodybuddy.exception;

public class CounsellingBoardException extends RuntimeException{
	public CounsellingBoardException(String msg) {
		super(msg);
	}
	public CounsellingBoardException(String msg, Throwable e) {
		super(msg, e);
	}
}
