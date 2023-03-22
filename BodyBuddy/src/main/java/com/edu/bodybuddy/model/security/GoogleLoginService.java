package com.edu.bodybuddy.model.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.security.MemberDetail;
import com.edu.bodybuddy.model.member.MemberService;
import com.edu.bodybuddy.social.GoogleLogin;
import com.edu.bodybuddy.social.GoogleOAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
@Service
public class GoogleLoginService implements SocialLoginService{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired private GoogleLogin googleLogin;
	@Autowired private RestTemplate restTemplate;
	@Autowired private MemberService memberService;
	@Autowired private UserDetailsService userDetailsService;
	
	@Override
	public String getGrantUrl() {
		return googleLogin.getGrantUrl();
	}
	
	@Override
	public MemberDetail getSocialInfo(String code) {
		//====토큰 취득을 위한 POST 헤더와 바디 구성하기====
		String url = googleLogin.getToken_request_url();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", googleLogin.getClient_id());
		params.add("client_secret", googleLogin.getClient_secret());
		params.add("redirect_uri", googleLogin.getRedirect_uri());
		params.add("grant_type", googleLogin.getGrant_type());
		
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		HttpEntity httpEntity=new HttpEntity(params, headers);
		
		//RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity=restTemplate.exchange(url,HttpMethod.POST, httpEntity, String.class);
		
		//====토큰 요청 후 ResponseEntity로부터 토큰 꺼내기====
		
		String body=responseEntity.getBody();
		log.info("구글에서 넘겨받은 응답정보"+body);
		
		//json 으로 되어있는 문자열을 파싱하여, 자바의 객체로 옮겨담자
		ObjectMapper objectMapper=new ObjectMapper();
		GoogleOAuthToken oAuthToken=null;
		
		try {
			oAuthToken=objectMapper.readValue(body, GoogleOAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//====토큰을 이용하여 회원정보에 접근====
		log.info("이 시점에서 로그인객체내용 :" + googleLogin);
		String userinfo_url=googleLogin.getUserinfo_url();
		
		//GET방식요청 구성 
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		HttpEntity entity=new HttpEntity(headers2);
		
		//비동기객체를 이용한 GET  요청
		//RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity=restTemplate.exchange(userinfo_url, HttpMethod.GET, entity, String.class);
		
		String userBody = userEntity.getBody();
		log.info("구글 회원정보 : "+userBody);
		
		HashMap<String, Object> userMap=new HashMap<String, Object>();
		//사용자 정보를 추출하여 맵으로 변환한다 
		ObjectMapper objectMapper2 = new ObjectMapper();
		try {
			userMap=objectMapper2.readValue(userBody, HashMap.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("구글 로그인 전체정보 " + userMap);
		//추출한 정보에서 필요한 정보 뽑아쓰기
		
		String id=(String)userMap.get("id");
		String nickname=(String)userMap.get("name");
		String email=(String)userMap.get("email");
		
		log.info("id"+id);
		log.info("email "+email);
		log.info("nickname "+nickname);
		
		MemberDetail existMember = null;
		try {
			existMember = (MemberDetail)userDetailsService.loadUserByUsername(email);
		} catch (UsernameNotFoundException e) {
			//어플리케이션을 처음 이용하는 경우 사용자 정보 등록
			Member member = new Member();
			member.setProvider("google");
			nickname = member.getProvider() + System.currentTimeMillis();
			member.setEmail(email);
			member.setNickname(nickname);
			memberService.regist(member);
			existMember = (MemberDetail)userDetailsService.loadUserByUsername(email);
			//닉네임 설정 페이지로 보내야한다
		} 
		return existMember;
	}

	
}
