package com.edu.bodybuddy.sns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
//소셜로그인에 사용할 객체
@Data
public class NaverLogin {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	//인증화면 관련
	private String endpoint;
	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String response_type;
	private String state;
	
	//토큰을 위한 변수 
	private String token_request_url;
	private String grant_type;
	
	//사용자 정보 조회를 위한 변수
	private String userinfo_url;
	
	
	//스프링 설정파일에서 넘겨받은 정보들을 이용하여 인증화면 링크 만들자 
	public String getGrantUrl() {
		StringBuilder sb=new StringBuilder();
		sb.append(endpoint+"?client_id="+client_id);
		sb.append("&redirect_uri="+redirect_uri);
		sb.append("&response_type="+response_type);
		sb.append("&state="+state);
		
		return sb.toString();
	}
	
}
