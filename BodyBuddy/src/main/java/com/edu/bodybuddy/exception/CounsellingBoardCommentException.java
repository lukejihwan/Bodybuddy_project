package com.edu.bodybuddy.exception;

public class CounsellingBoardCommentException extends RuntimeException{
	public CounsellingBoardCommentException(String msg) {
		super(msg);
	}
	public CounsellingBoardCommentException(String msg, Throwable e) {
		super(msg, e);
	}
}
