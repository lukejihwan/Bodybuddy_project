package com.edu.bodybuddy.controller.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.edu.bodybuddy.domain.member.Role;
import com.edu.bodybuddy.exception.AddressException;
import com.edu.bodybuddy.exception.MemberException;
import com.edu.bodybuddy.exception.PasswordException;
import com.edu.bodybuddy.model.member.MemberService;
import com.edu.bodybuddy.sns.NaverLogin;
import com.edu.bodybuddy.sns.NaverOAuthToken;
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
	private final MemberService memberService;
	
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
	
	@GetMapping("/{vendor}")
	@ResponseBody
	public ResponseEntity<Msg> getSocialLoginUrl(@PathVariable String vendor) {
		log.info("벤더 요청 들어옴");
		String url = null;
		switch(vendor) {
			case "naver" : { url = naverLogin.getGrantUrl(); break;}
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
		params.add("grant_type", naverLogin.getState());
		
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
		
		//사용자 정보 추출하기 
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
		
		Map response = (Map)userMap.get("response");
		
		
		String id=(String)response.get("id");
		String nickname=(String)response.get("nickname");
		String email=(String)response.get("email");
		String mobile=(String)response.get("mobile");
		
		
		log.info("id"+id);
		log.info("email "+email);
		log.info("nickname "+nickname);
		log.info("mobile "+mobile);
		
		Member member = new Member();
		member.setEmail(email);

		
		Member exist = memberService.selectByEmail(member); 
		ModelAndView mv = new ModelAndView();
		if(exist==null) {
			//회원가입을 위해 닉네임 설정 페이지로 보내기
			//여기서부터는 테스트 코드
			member.setProvider("naver");
			member.setPhone(mobile);
			member.setRole(Role.ROLE_USER);
			Password password = new Password();
			password.setPass(id);
			member.setPassword(password);
			nickname = member.getProvider() + System.currentTimeMillis();
			member.setNickname(nickname);
			memberService.regist(member);
			mv.addObject("Member", member);
		}else {
			//그렇지 않은경우
			//로그인 처리만 하자 (세션에 담자)
			exist.getPassword().setPass(id);
			mv.addObject("Member", exist);
		}
		mv.setViewName("member/login_ic");
		
		return mv;
	}
	
	@ExceptionHandler({MemberException.class, AddressException.class, PasswordException.class})
	public ModelAndView handle(Exception e) {
		ModelAndView mv = new ModelAndView("user/member/error");
		mv.addObject("e", e);
		return mv;
	}
	
}
