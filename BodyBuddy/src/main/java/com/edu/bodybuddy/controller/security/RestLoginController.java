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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.security.MemberDetail;
import com.edu.bodybuddy.util.Msg;
@RequestMapping("/rest")
public class RestLoginController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	
	   @PostMapping("/login/android")
	   @ResponseBody
	   public ResponseEntity<Msg> androidLogin(Member member){
	      MemberDetail memberDetail = new MemberDetail(member);
	      log.info("넘어온 member : " + member);
	      log.info("만든 디테일 : "+ memberDetail);
	      UsernamePasswordAuthenticationToken test = new UsernamePasswordAuthenticationToken(memberDetail, member.getPassword().getPass(), null);
	      SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(test));
	      ResponseEntity entity=new ResponseEntity<Msg>(new Msg("로그인 성공"), HttpStatus.OK);
	      return null;
	   }

	   
}
