package com.edu.bodybuddy.util;

import lombok.Data;

@Data
public class Message {
	private String msg;
	private int code;
	public Message() {}
	public Message(String msg) {
		this.msg = msg;
	}
	public Message(int code) {
		this.code = code;
	}
	public Message(String msg, int code) {
		this.msg = msg;
		this.code = code;
	}
}
