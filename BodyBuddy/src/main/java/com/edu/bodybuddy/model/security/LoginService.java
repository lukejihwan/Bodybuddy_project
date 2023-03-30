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
 * DB에서 불러온 유저정보를 이용하기 위한 객체 
 * */
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService{

	private Logger log = LoggerFactory.getLogger(getClass());
    private final MemberDAO memberDAO;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
    	//이메일 조회하기 위한 멤버객체 생성
    	Member emailCheck = new Member();
    	emailCheck.setEmail(email);
    	//조회
        Member member = memberDAO.selectByEmail(emailCheck);
        if(member==null){
        	//일치하는 멤버 없으면 예외처리
            throw new UsernameNotFoundException("userEmail" + email + " not found");
        }
        
        return new MemberDetail(member);
    }

}
