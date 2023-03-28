package com.edu.bodybuddy.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.PasswordException;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class MybatisPasswordDAO implements PasswordDAO{

	private final SqlSessionTemplate sqlSessionTemplate;
	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void insert(Member member) throws PasswordException{
		int result = sqlSessionTemplate.insert("Password.insert", member);
		if(result <1) throw new PasswordException("비밀번호 등록 실패");
	}

	@Override
	public void update(Member member) throws PasswordException{
		int result = sqlSessionTemplate.update("Password.update", member);
		if(result <1) throw new PasswordException("비밀번호 등록 실패");
	}

	@Override
	public void delete(Member member) throws PasswordException{
		int result = sqlSessionTemplate.delete("Password.delete", member);
		if(result <1) throw new PasswordException("비밀번호 삭제 실패");
	}

}
