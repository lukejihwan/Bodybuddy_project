package com.edu.bodybuddy.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
//소셜로그인에 사용할 객체
@Data
public class KakaoLogin implements SocialLogin{
	private String endpoint;
	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String response_type;
	
	private String token_request_url;
	private String grant_type;
	
	private String userinfo_url;
	
	public String getGrantUrl() {
		StringBuilder sb=new StringBuilder();
		sb.append(endpoint+"?client_id="+client_id);
		sb.append("&redirect_uri="+redirect_uri);
		sb.append("&response_type="+response_type);
		
		return sb.toString();
	}
	
}
