package com.edu.bodybuddy.controller.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.security.MemberDetail;
import com.edu.bodybuddy.model.member.MemberDAO;
import com.edu.bodybuddy.model.member.MemberService;
@RequestMapping("/auth/rest/login")
@RestController
public class RestLoginController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
	private MemberService memberService;
	
	
	   @PostMapping("/android")
	   public ResponseEntity<Member> androidLogin(@RequestBody Member member){
		   log.info("응답 받음");
		   
	      MemberDetail memberDetail = new MemberDetail(member);
	      log.info("넘어온 member : " + member);
	      log.info("만든 디테일 : "+ memberDetail);
	      UsernamePasswordAuthenticationToken memberCheck = new UsernamePasswordAuthenticationToken(memberDetail, member.getPassword().getPass(), null);
	      SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(memberCheck));
	      
	      Member result = memberService.selectByEmail(member);
	      
	      ResponseEntity entity=new ResponseEntity<Member>(result, HttpStatus.OK);
	      
	      return entity;
	   }

	   
}
