package com.edu.bodybuddy.controller.user;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.AddressException;
import com.edu.bodybuddy.exception.EmailException;
import com.edu.bodybuddy.exception.MemberException;
import com.edu.bodybuddy.exception.PasswordException;
import com.edu.bodybuddy.model.member.MemberService;
import com.edu.bodybuddy.util.EmailManager;
import com.edu.bodybuddy.util.Msg;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/member")
public class RestMemberController {
	private Logger log = LoggerFactory.getLogger(getClass());
	private final MemberService memberService;
	private final EmailManager emailManager;
	@PostMapping
	public ResponseEntity<Msg> regist(Member member, HttpSession session) {
		log.info("회원가입에 넘어온 멤버"+member);
		
		Msg msg = new Msg();
		
		Boolean isVerified = (Boolean)session.getAttribute("isVerified");
		log.info("이메일 인증여부 : " +isVerified);
		if(!isVerified) {
			msg.setMsg("이메일 주소가 인증되지 않았습니다");
			ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
			return entity;
		}
		
		//이 요청으로 가입하는 회원은 일반 가입 회원이므로, 프로바이더를 home으로 정해준다
		member.setProvider("home");
		memberService.regist(member);
		
		msg.setMsg("회원가입을 축하드립니다");
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/email")
	//이메일 인증코드 발송 요청
	public ResponseEntity<Msg> sendVerifyEmail(Member member, HttpSession session){
		memberService.emailCheck(member);
		String code = emailManager.sendAuthCode(member);
		log.info("발송된 코드 : "+code);
		session.setAttribute("emailCode", code);
		Msg msg = new Msg();
		msg.setMsg("인증 확인 메일이 발송되었습니다");
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/email")
	//이메일 코드 확인
	public ResponseEntity<Msg> verifyEmail(String code, HttpSession session){
		String sessionCode = (String)session.getAttribute("emailCode");
		log.info("세션 코드 : "+code);
		Msg msg = new Msg();
		
		if(!code.equals(sessionCode)) {
			msg.setMsg("인증번호가 일치하지 않습니다");
			return new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		session.setAttribute("isVerified", true);
		msg.setMsg("이메일 인증이 완료되었습니다");
		return new ResponseEntity<Msg>(msg, HttpStatus.OK);
	}
	
	
	@ExceptionHandler({MemberException.class, AddressException.class, PasswordException.class, EmailException.class})
	public ResponseEntity<Msg> handle(Exception e){
		Msg msg = new Msg();
		msg.setMsg(e.getMessage());
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
