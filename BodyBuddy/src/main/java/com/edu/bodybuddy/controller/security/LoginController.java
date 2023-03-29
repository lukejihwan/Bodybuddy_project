package com.edu.bodybuddy.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.security.MemberDetail;
import com.edu.bodybuddy.exception.AddressException;
import com.edu.bodybuddy.exception.MemberException;
import com.edu.bodybuddy.exception.PasswordException;
import com.edu.bodybuddy.model.security.GoogleLoginService;
import com.edu.bodybuddy.model.security.KakaoLoginService;
import com.edu.bodybuddy.model.security.NaverLoginService;
import com.edu.bodybuddy.util.Msg;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private final NaverLoginService naverLoginService;
	private final GoogleLoginService googleLoginService;
	private final KakaoLoginService kakaoLoginService;
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@GetMapping("/login")
	public String getLoginPage() {
		log.info("로그인 페이지 요청 들어옴");
		return "member/loginpage";
	}
	
	@GetMapping("/join")
	public String getJoinPage(HttpSession session) {
		log.info("회원가입 페이지 요청 들어옴");
		session.setAttribute("isVerified", false);
		return "member/joinpage";
	}
	
	@PostMapping("/login_error")
	public String loginError() {
		return "member/login_error";
	}
	
	@GetMapping("/social/{vendor}")
	@ResponseBody
	public ResponseEntity<Msg> getSocialLoginUrl(@PathVariable String vendor, HttpSession session) {
		log.info(vendor + " 로그인 요청 들어옴");
		String url = null;
		switch(vendor) {
			case "naver" : { url = naverLoginService.getGrantUrl(); break;}
			case "google" : { url = googleLoginService.getGrantUrl(); break;}
			case "kakao" : { url = kakaoLoginService.getGrantUrl(); break;}
		}
		Msg msg = new Msg();
		msg.setMsg(url);
		session.setAttribute("emailCode", 102094);
		ResponseEntity entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping("/callback/{vendor}")
	public ModelAndView getNaverToken(@PathVariable String vendor, HttpServletRequest req){
		
		String code = req.getParameter("code");
		log.info("받아온 "+vendor+" code : " + code);
		
		int emailCode = (int)req.getSession().getAttribute("emailCode");
		log.info("들어있나? " + emailCode);
		
		MemberDetail existMember = null; 
		switch (vendor) {
			case "naver": {existMember = naverLoginService.getSocialInfo(code); break; }
			case "google": {existMember = googleLoginService.getSocialInfo(code); break; }
			case "kakao": {existMember = kakaoLoginService.getSocialInfo(code); break; }
		}
		
		//유저 정보를 session에 담는다 => 로그인 처리
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(existMember,null,existMember.getAuthorities()));
		ModelAndView mv = new ModelAndView("redirect:/mypage");
		return mv;
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Msg> androidLogin(Member member){
		MemberDetail memberDetail = new MemberDetail(member);
		log.info("넘어온 member : " + member);
		log.info("만든 디테일 : "+ memberDetail);
		UsernamePasswordAuthenticationToken test = new UsernamePasswordAuthenticationToken(memberDetail, member.getPassword().getPass(), null);
		SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(test));
		ResponseEntity entity=new ResponseEntity<Msg>(new Msg("로그인 성공"), HttpStatus.OK);
		return entity;
	}
	
	@ExceptionHandler({MemberException.class, AddressException.class, PasswordException.class, UsernameNotFoundException.class})
	public ModelAndView handle(Exception e) {
		ModelAndView mv = new ModelAndView("error/error");
		mv.addObject("e", e);
		return mv;
	}
	
}
