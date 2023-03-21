package com.edu.bodybuddy.social;

import lombok.Data;

//네이버 문자인증 서비스를 이용하기 위한 요청정보를 담을 객체
@Data
public class NaverSMS {
	private String hostNameUrl; // 호스트 URL
	private String requestUrl; // 요청 URL
	private String requestUrlType;
	private String accessKey; 
	private String secretKey;	
	private String serviceId;
	private String method;
	private String adminPhone;
	
	public String getSignatureUrl() {
		return requestUrl+serviceId+requestUrlType;
	}
	
	public String getApiUrl() {
		return hostNameUrl+getSignatureUrl();
	}
}
