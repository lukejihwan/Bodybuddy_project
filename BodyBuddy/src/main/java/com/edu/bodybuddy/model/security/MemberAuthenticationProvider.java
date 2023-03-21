package com.edu.bodybuddy.model.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.edu.bodybuddy.domain.security.MemberDetail;
import com.edu.bodybuddy.exception.LoginException;

/*
사용자 인증 과정에서 암호화된 패스워드를 비교하기 위해 provider를 커스터마이징 하기 위한 객체
*/
@Component
public class MemberAuthenticationProvider implements AuthenticationProvider {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private PasswordEncoder passwordEncoder;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        //로그인을 시도한 정보를 받아온다
        String email = authentication.getName();
        String password = (String)authentication.getCredentials();
        
        log.info("로그인 시도한 이메일 : "+email);
        log.info("로그인 시도한 비밀번호 : "+password);

        
        MemberDetail userDetails = (MemberDetail) userDetailsService.loadUserByUsername(email);
        
//        //소셜로그인 유저라면 바로 인증해준다
//        if(!userDetails.getMember().getProvider().equals("home")) {
//        	userDetails.getMember().setPassword(null);
//            return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
//        }
        
        //일반가입 유저라면 DB에서 로그인 정보와 일치하는 사용자 정보를 찾아 DTO에 담아 비교
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            log.info("password 불일치 : 입력한 패스워드 = "+password + "// DB 패스워드 = " + userDetails.getPassword());
            throw new LoginException("로그인 실패, 정보를 확인하세요");
        }
        log.info("password 일치 : 입력한 패스워드 = "+password + "// DB 패스워드 = " + userDetails.getPassword());
        log.info("권한은 : "+userDetails.getAuthorities());
        //일치하는 사용자가 있을 경우 인증 토큰을 발급한다
        return new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //커스텀 프로바이더를 사용할지 여부를 결정한다
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
