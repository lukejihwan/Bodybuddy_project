package com.edu.bodybuddy.model.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
/*
 * 스프링 시큐리티 커스터마이징을 위해 프레임워크에서 요구하는 정보 리턴을 위하여 
 * UserDetails를 구현한 객체
 * */
@RequiredArgsConstructor
@Getter
@ToString
public class MemberDetail implements UserDetails{

	  private final Member member;

	    //가입된 회원의 인증정보를 불러와 리턴
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        ArrayList<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
	        roleList.add(new SimpleGrantedAuthority(member.getRole().toString()));
	        return roleList;
	    }

	    //패스워드 비교를 위해 패스워드 리턴
	    @Override
	    public String getPassword() {
	        return member.getPassword().getPass();
	    }

	    //이메일ID 비교를 위해 리턴
	    @Override
	    public String getUsername() {
	        return member.getEmail();
	    }

	    /*
	    * 아래의 정보들은 DB에 테이블을 따로 두어 관리할 수 있지만
	    * 현재 어플리케이션에서는 사용하지 않을 기능들이기 때문에 전부 true를 리턴한다
	    * */
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
}
