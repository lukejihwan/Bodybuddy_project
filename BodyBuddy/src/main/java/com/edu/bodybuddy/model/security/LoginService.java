package com.edu.bodybuddy.model.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.security.MemberDetail;
import com.edu.bodybuddy.model.member.MemberDAO;

import lombok.RequiredArgsConstructor;

/*
 * 스프링 시큐리티 로그인 과정을 커스터마이징하여
 * DB에서 불러온 유저정보를 이용하기 위한 객체 
 * */
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService{

	private Logger log = LoggerFactory.getLogger(getClass());
    private final MemberDAO memberDAO;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	log.info("여기 진입함");
    	Member emailCheck = new Member();
    	emailCheck.setEmail(email);
        Member member = memberDAO.selectByEmail(emailCheck);
        log.info("받아온 멤버는 : "+member);
        if(member==null){
            throw new UsernameNotFoundException("userEmail" + email + " not found");
        }
        log.info("=================found user===================");
        log.info("email : "+member.getEmail());

        return new MemberDetail(member);
    }

}
