package com.edu.bodybuddy.controller.security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.edu.bodybuddy.model.security.MemberDetail;

/*
사용자 인증 과정에서 암호화된 패스워드를 비교하기 위해 provider를 커스터마이징 하기 위한 객체
*/
@Component
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider {
    private Logger log = LoggerFactory.getLogger(getClass());
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        //로그인을 시도한 정보를 받아온다
        String email = authentication.getName();
        log.info("auth 객체 내용 : " +authentication);
        String password = (String)authentication.getCredentials();

        log.info("받아온 이름은" + email);

        //DB에서 로그인 정보와 일치하는 사용자 정보를 찾아 DTO에 담아 비교
        UserDetails userDetails = (MemberDetail) userDetailsService.loadUserByUsername(email);
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            log.info("password 불일치 : 입력한 패스워드 = "+password + "// DB 패스워드 = " + userDetails.getPassword());
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }
        log.info("password 일치 : 입력한 패스워드 = "+password + "// DB 패스워드 = " + userDetails.getPassword());
        log.info("권한은 : "+userDetails.getAuthorities());
        //일치하는 사용자가 있을 경우 인증 토큰을 발급한다
        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //인증 토큰이 변조되지 않았는지 확인한다
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
