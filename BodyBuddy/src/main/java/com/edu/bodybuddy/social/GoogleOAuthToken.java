package com.edu.bodybuddy.social;

import lombok.Data;

@Data
public class GoogleOAuthToken {
	private String access_token;
	private int expires_in; //유효기간
	private String scope;
	private String token_type;
	private String id_token;
	
}