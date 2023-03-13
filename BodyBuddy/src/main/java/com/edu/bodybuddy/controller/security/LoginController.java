package com.edu.bodybuddy.controller.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
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
}
