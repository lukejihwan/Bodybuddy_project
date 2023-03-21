package com.edu.bodybuddy.util;

import lombok.Data;
@Data
//클라이언트에게 응답으로 보낼 메시지 객체
public class Msg {
	private int code;
	private String msg;
	
	public Msg() {
	}
	public Msg(String msg) {
		this.msg = msg;
	}
	
	
}
