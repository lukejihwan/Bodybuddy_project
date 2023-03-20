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
import com.edu.bodybuddy.social.KakaoLogin;
import com.edu.bodybuddy.social.KakaoOAuthToken;
import com.edu.bodybuddy.util.EmailManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class KakaoLoginService implements SocialLoginService{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired private KakaoLogin kakaoLogin;
	@Autowired private RestTemplate restTemplate;
	@Autowired private MemberService memberService;
	@Autowired private UserDetailsService userDetailsService;
	
	
	@Override
	public String getGrantUrl() {
		return kakaoLogin.getGrantUrl();
	}
	
	@Override
	public MemberDetail getSocialInfo(String code) {
		String url = kakaoLogin.getToken_request_url();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", kakaoLogin.getClient_id());
		params.add("client_secret", kakaoLogin.getClient_secret());
		params.add("redirect_uri", kakaoLogin.getRedirect_uri());
		params.add("grant_type", kakaoLogin.getGrant_type());
		
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		HttpEntity httpEntity=new HttpEntity(params, headers);
		ResponseEntity<String> responseEntity=restTemplate.exchange(url,HttpMethod.POST, httpEntity, String.class);
		
		String body=responseEntity.getBody();
		log.info("카카오에서 넘겨받은 응답정보"+body);
		
		ObjectMapper objectMapper=new ObjectMapper();
		KakaoOAuthToken oAuthToken=null;
		
		try {
			oAuthToken=objectMapper.readValue(body, KakaoOAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//====토큰을 이용하여 회원정보에 접근====
		log.info("이 시점에서 카카오로그인객체내용 :" + kakaoLogin);
		String userinfo_url=kakaoLogin.getUserinfo_url();
		
		//GET방식요청 구성 
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		HttpEntity entity=new HttpEntity(headers2);
		
		//비동기객체를 이용한 GET  요청
		RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity=restTemplate2.exchange(userinfo_url, HttpMethod.GET, entity, String.class);
		
		String userBody = userEntity.getBody();
		log.info("카카오 회원정보 : "+userBody);
		
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
		
		log.info("카카오 로그인 전체정보 " + userMap);
		
		//추출한 정보에서 필요한 정보 뽑아쓰기
		Map kakao_account = (Map)userMap.get("kakao_account");
		Map profile=(Map)kakao_account.get("profile");
		
		String email=(String)kakao_account.get("email");
		String nickname = (String)profile.get("nickname");
		log.info("kakao_account " + kakao_account);
		log.info("email "+email);
		log.info("nickname "+nickname);
		
		MemberDetail existMember = null; 
		
		try {
			existMember = (MemberDetail)userDetailsService.loadUserByUsername(email);
		} catch (UsernameNotFoundException e) {
			//어플리케이션을 처음 이용하는 경우 사용자 정보 등록
			Member member = new Member();
			member.setProvider("kakao");
			member.setPhone("");
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
