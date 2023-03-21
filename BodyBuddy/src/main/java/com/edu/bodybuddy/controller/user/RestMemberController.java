package com.edu.bodybuddy.controller.user;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.AddressException;
import com.edu.bodybuddy.exception.EmailException;
import com.edu.bodybuddy.exception.MemberException;
import com.edu.bodybuddy.exception.PasswordException;
import com.edu.bodybuddy.exception.SMSException;
import com.edu.bodybuddy.model.member.MemberService;
import com.edu.bodybuddy.util.EmailManager;
import com.edu.bodybuddy.util.Msg;
import com.edu.bodybuddy.util.SmsManager;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/member")
public class RestMemberController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private final MemberService memberService;
	private final EmailManager emailManager;
	private final SmsManager smsManager;
	
	@PostMapping
	public ResponseEntity<Msg> regist(Member member, HttpSession session) {
		log.info("회원가입에 넘어온 멤버"+member);
		
		Msg msg = new Msg();
		
		Boolean isEmailVerified = (Boolean)session.getAttribute("isEmailVerified");
		Boolean isPhoneVerified = (Boolean)session.getAttribute("isPhoneVerified");
		log.info("이메일 인증여부 : " +isEmailVerified);
		log.info("휴대폰 인증여부 : " +isPhoneVerified);
		
		//이메일 인증여부와 전화번호 인증여부를 체크한다
		if(!isEmailVerified) {
			msg.setMsg("이메일 주소가 인증되지 않았습니다");
			ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
			return entity;
		}else if(!isPhoneVerified) {
			msg.setMsg("전화번호가 인증되지 않았습니다");
			ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
			return entity;
		}
		
		/*
		 * 인증이 모두 확인되면 회원가입 진행 
		 * 이 요청으로 가입하는 회원은 일반 가입 회원이므로, 프로바이더를 home으로 정해준다
		 * */
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
		msg.setMsg("확인 메일이 발송되었습니다. 인증 코드를 입력하세요");
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/email")
	//이메일 코드 확인
	public ResponseEntity<Msg> verifyEmail(String code, HttpSession session){
		String sessionCode = (String)session.getAttribute("emailCode");
		log.info("세션 코드 : "+sessionCode);
		log.info("유저가 입력한 코드 : "+code);
		Msg msg = new Msg();
		
		if(!code.equals(sessionCode)) {
			msg.setMsg("인증번호가 일치하지 않습니다");
			return new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		session.setAttribute("isEmailVerified", true);
		msg.setMsg("이메일 인증이 완료되었습니다");
		return new ResponseEntity<Msg>(msg, HttpStatus.OK);
	}
	
	@PostMapping("/check/{item}")
	public ResponseEntity<Msg> nicknameCheck(@PathVariable String item, Member member){
		Msg msg = new Msg();
		if(item.equals("nickname")) {
			memberService.nicknameCheck(member);
			msg.setMsg("사용 가능한 닉네임입니다");
		} else {
			memberService.emailCheck(member);
			msg.setMsg("사용 가능한 이메일입니다");
		}
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	/* 전화번호 인증 SMS 발송 메서드*/
	@GetMapping("/phone")
	public ResponseEntity<Msg> sendSMS(Member member, HttpSession session){
		//SMS 매니저를 통해 SMS를 발송하고 인증코드를 반환받아 세션에 저장한다
		String code = smsManager.sendSMS(member);
		session.setAttribute("smsCode", code);
		log.info("세션에 저장된 SMS 코드 : "+code);
		Msg msg = new Msg("인증 문자가 발송되었습니다");
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	} 
	
	/* 유저가 입력한 코드와 세션의 코드를 비교해서 인증여부를 확인하자*/
	@PostMapping("/phone")
	public ResponseEntity<Msg> verifyPhone(String codeP, HttpSession session){
		//SMS 매니저를 통해 SMS를 발송하고 인증코드를 반환받아 세션에 저장한다
		String sessionCode = (String)session.getAttribute("smsCode");
		log.info("세션 코드 : "+sessionCode);
		log.info("유저가 입력한 코드 : "+codeP);
		Msg msg = new Msg();
		//일치하지 않는 경우 에러
		if(!codeP.equals(sessionCode)) {
			msg.setMsg("인증번호가 일치하지 않습니다");
			return new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//일치하는 경우 세션에 결과값을 담고 성공메시지 반환
		session.setAttribute("isPhoneVerified", true);
		msg.setMsg("전화번호 인증이 완료되었습니다");
		return new ResponseEntity<Msg>(msg, HttpStatus.OK);
	} 
	
	
	
	@ExceptionHandler({MemberException.class, AddressException.class, PasswordException.class, EmailException.class, SMSException.class})
	public ResponseEntity<Msg> handle(Exception e){
		Msg msg = new Msg();
		msg.setMsg(e.getMessage());
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
