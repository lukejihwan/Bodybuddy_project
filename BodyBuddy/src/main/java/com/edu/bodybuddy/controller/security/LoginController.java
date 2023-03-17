package com.edu.bodybuddy.controller.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.member.Password;
import com.edu.bodybuddy.domain.security.MemberDetail;
import com.edu.bodybuddy.exception.AddressException;
import com.edu.bodybuddy.exception.MemberException;
import com.edu.bodybuddy.exception.PasswordException;
import com.edu.bodybuddy.model.member.MemberService;
import com.edu.bodybuddy.social.GoogleLogin;
import com.edu.bodybuddy.social.KakaoLogin;
import com.edu.bodybuddy.social.KakaoOAuthToken;
import com.edu.bodybuddy.social.NaverLogin;
import com.edu.bodybuddy.social.NaverOAuthToken;
import com.edu.bodybuddy.util.Msg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private final NaverLogin naverLogin;
	private final KakaoLogin kakaoLogin;
	private final GoogleLogin googleLogin;
	private final MemberService memberService;
	@Autowired private UserDetailsService userDetailsService;
	
	@GetMapping("/login")
	public String getLoginPage() {
		log.info("로그인 페이지 요청 들어옴");
		return "member/loginpage";
	}
	
	@GetMapping("/join")
	public String getJoinPage() {
		log.info("회원가입 페이지 요청 들어옴");
		return "member/joinpage";
	}
	
	@PostMapping("/login_error")
	public String loginError() {
		return "member/login_error";
	}
	
	@GetMapping("/social/{vendor}")
	@ResponseBody
	public ResponseEntity<Msg> getSocialLoginUrl(@PathVariable String vendor) {
		log.info(vendor + " 로그인 요청 들어옴");
		String url = null;
		switch(vendor) {
			case "naver" : { url = naverLogin.getGrantUrl(); break;}
			case "kakao" : { url = kakaoLogin.getGrantUrl(); break;}
			case "google" : { url = googleLogin.getGrantUrl(); break;}
		}
		Msg msg = new Msg();
		msg.setMsg(url);

		ResponseEntity entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	
	@GetMapping("/callback/naver")
	public ModelAndView getNaverToken(HttpServletRequest req){
		
		String code = req.getParameter("code");
		log.info("받아온 user code : " + code);
		
		//====토큰 취득을 위한 POST 헤더와 바디 구성하기====
		String url = naverLogin.getToken_request_url();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", naverLogin.getClient_id());
		params.add("client_secret", naverLogin.getClient_secret());
		params.add("redirect_uri", naverLogin.getRedirect_uri());
		params.add("grant_type", naverLogin.getGrant_type());
		params.add("state", naverLogin.getState());
		
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		HttpEntity httpEntity=new HttpEntity(params, headers);
		
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity=restTemplate.exchange(url,HttpMethod.POST, httpEntity, String.class);
		
		//====토큰 요청 후 ResponseEntity로부터 토큰 꺼내기====
		
		String body=responseEntity.getBody();
		log.info("네이버에서 넘겨받은 응답정보"+body);
		
		//json 으로 되어있는 문자열을 파싱하여, 자바의 객체로 옮겨담자
		ObjectMapper objectMapper=new ObjectMapper();
		NaverOAuthToken oAuthToken=null;
		
		try {
			oAuthToken=objectMapper.readValue(body, NaverOAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//====토큰을 이용하여 회원정보에 접근====
		log.info("이 시점에서 로그인객체내용 :" + naverLogin);
		String userinfo_url=naverLogin.getUserinfo_url();
		
		//GET방식요청 구성 
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		HttpEntity entity=new HttpEntity(headers2);
		
		//비동기객체를 이용한 GET  요청
		RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity=restTemplate2.exchange(userinfo_url, HttpMethod.GET, entity, String.class);
		
		String userBody = userEntity.getBody();
		log.info("네이버 회원정보 : "+userBody);
		
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
		
		//추출한 정보에서 필요한 정보 뽑아쓰기
		Map response = (Map)userMap.get("response");
		
		String id=(String)response.get("id");
		String nickname=(String)response.get("nickname");
		String email=(String)response.get("email");
		String mobile=(String)response.get("mobile");
		
		log.info("id"+id);
		log.info("email "+email);
		log.info("nickname "+nickname);
		log.info("mobile "+mobile);
		
		MemberDetail existMember = null;
		log.info("이메일로 불러온 naver 객체는 " + existMember);
		try {
			existMember = (MemberDetail)userDetailsService.loadUserByUsername(email);
		} catch (UsernameNotFoundException e) {
			//어플리케이션을 처음 이용하는 경우 사용자 정보 등록
			Member member = new Member();
			member.setProvider("naver");
			member.setPhone(mobile);
			nickname = member.getProvider() + System.currentTimeMillis();
			member.setEmail(email);
			member.setNickname(nickname);
			memberService.regist(member);
			existMember = (MemberDetail)userDetailsService.loadUserByUsername(email);
			//닉네임 설정 페이지로 보내야한다
		} 
			
		//유저 정보를 session에 담는다 => 로그인 처리
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(existMember,existMember.getPassword(),existMember.getAuthorities()));
		ModelAndView mv = new ModelAndView("redirect:/mypage");
		return mv;
	}
	
	
	@GetMapping("/callback/kakao")
	public ModelAndView getKakaoToken(HttpServletRequest req){
		
		String code = req.getParameter("code");
		log.info("받아온 카카오 code : " + code);
		
		//====토큰 취득을 위한 POST 헤더와 바디 구성하기====
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
		
		RestTemplate restTemplate=new RestTemplate();
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
		
		//추출한 정보에서 필요한 정보 뽑아쓰기
		Map kakao_account = (Map)userMap.get("kakao_account");
		Map profile=(Map)kakao_account.get("profile");
		
		String email=(String)kakao_account.get("email");
		String nickname = (String)profile.get("nickname");
		log.info("kakao_account " + kakao_account);
		log.info("email "+email);
		log.info("nickname "+nickname);
		
		MemberDetail existMember = null; 
		
		log.info("이메일로 불러온 kakao 객체는! " + existMember);
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
		//유저 정보를 session에 담는다 => 로그인 처리
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(existMember,existMember.getPassword(),existMember.getAuthorities()));
		ModelAndView mv = new ModelAndView("redirect:/mypage");
		return mv;
	}
	
	@ExceptionHandler({MemberException.class, AddressException.class, PasswordException.class, UsernameNotFoundException.class})
	public ModelAndView handle(Exception e) {
		ModelAndView mv = new ModelAndView("error/error");
		mv.addObject("e", e);
		return mv;
	}
	
}
