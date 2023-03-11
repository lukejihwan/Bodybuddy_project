package com.edu.bodybuddy.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.MemberException;
import com.edu.bodybuddy.model.member.MemberService;
import com.edu.bodybuddy.util.Msg;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/user/member")
public class RestMemberController {
	private Logger log = LoggerFactory.getLogger(getClass());
	private final MemberService memberService;
	
	@PostMapping
	public ResponseEntity<Msg> regist(Member member) {
		log.info("넘어온거"+member);
		//memberService.regist(member);
		Msg msg = new Msg();
		msg.setMsg("회원가입을 축하드립니다");
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	@ExceptionHandler(MemberException.class)
	public ResponseEntity<Msg> handle(MemberException e){
		Msg msg = new Msg();
		msg.setMsg(e.getMessage());
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
