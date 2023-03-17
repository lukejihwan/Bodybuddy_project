package com.edu.bodybuddy.sns;

import lombok.Data;

//네이버 서버에서 전송받은 json 문자열을 자바의 객체로 담아놓기 위한 목적 
@Data
public class KakaoOAuthToken {
	private String access_token;
	private String refresh_token; //재발급에 필요한 정보
	private int expires_in; //유효기간
	private String token_type;
	
}