package com.edu.bodybuddy.controller.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.bodybuddy.sns.NaverLogin;
import com.edu.bodybuddy.util.Msg;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private final NaverLogin naverLogin;
	
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
	
	@PostMapping("/callback/{vendor}")
	public ResponseEntity<Msg> getNaverToken(@PathVariable String vendor, HttpServletRequest req){
		log.info(vendor+" 서비스에서 들어온 콜백");
		
		String code = req.getParameter("code");
		log.info("받아온 user code : " + code);
		String url = "";
		
		return null;
	}
}
