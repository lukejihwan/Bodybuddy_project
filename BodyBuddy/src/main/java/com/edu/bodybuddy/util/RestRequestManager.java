package com.edu.bodybuddy.util;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.fabric.xmlrpc.base.Params;

@Component
public class RestRequestManager {
//	private MultiValueMap<String, String> params;
	private StringBuilder sb;
	private HttpHeaders header;
	private boolean flag;

	public RestRequestManager() {
		init();
	}
	
	public void init() {
//		params = new LinkedMultiValueMap<String, String>();
		sb = new StringBuilder();
		header = new HttpHeaders();
	}
	
//	public void addParam(String key, String value) {
//		params.add(key, value);
//	}
//	public void removeParam(String key) {
//		params.remove(key);
//	}
	public void addParam(String key, String value) {
		if(!flag) {
			sb.append("?"+key+"="+value);
			flag=true;
		}else {
			sb.append("&"+key+"="+value);
		}
	}
	public void addHeader(String key, String value) {
		header.set(key, value);
	}
	public void removeHeader(String key) {
		header.remove(key);
	}
	public ResponseEntity<String> request(String url, HttpMethod method) {
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity(header);
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		RestTemplate template = new RestTemplate();
		
		//System.out.println("params : "+params.toString());
//		System.out.println("header : "+header.toString());
//		System.out.println("entity : "+requestEntity.toString());
//		System.out.println("url : "+url);
		
		ResponseEntity<String> responseEntity = template.exchange(url+sb.toString(), method, requestEntity, String.class);
		
		return responseEntity;
	}
}
