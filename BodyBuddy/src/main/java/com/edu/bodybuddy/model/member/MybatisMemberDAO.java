package com.edu.bodybuddy.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.MemberException;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class MybatisMemberDAO implements MemberDAO {
	
	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Member selectByIdPass(Member member) {
		Member dto = sqlSessionTemplate.selectOne("Member.selectByIdPass", member);
		if(dto == null) throw new MemberException("로그인 실패, 아이디와 비밀번호를 확인하세요");
		return dto;
	}
	
	@Override
	//아이디 중복 체크
	public void selectById(Member member) throws MemberException{
		Member dto = sqlSessionTemplate.selectOne("Member.selectById", member);
		if(dto!=null) throw new MemberException("이미 존재하는 아이디입니다");
	}

	@Override
	public void selectByEmail(Member member) {
		Member dto = sqlSessionTemplate.selectOne("Member.selectByEmail", member);
		if(dto!=null) throw new MemberException("이미 존재하는 이메일입니다");
	}

	@Override
	public List<Member> selectAll() {
		return sqlSessionTemplate.selectList("Member.selectAll");
	}

	@Override
	public int insert(Member member) throws MemberException{
		int result = sqlSessionTemplate.insert("Member.insert", member);
		if(result<1) throw new MemberException("회원 등록에 실패했습니다");
		return result;
	}

	@Override
	public void update(Member member) throws MemberException{
		int result = sqlSessionTemplate.update("Member.update", member);
		if(result<1) throw new MemberException("회원정보 수정에 실패했습니다");
	}

	@Override
	public void updatePass(Member member) throws MemberException{
		int result = sqlSessionTemplate.update("Member.updatePass", member);
		if(result<1) throw new MemberException("비밀번호 변경에 실패했습니다");
	}

	@Override
	public void delete(int member_idx) throws MemberException{
		int result = sqlSessionTemplate.delete("Member.update", member_idx);
		if(result<1) throw new MemberException("회원 탈퇴에 실패했습니다. 관리자에게 문의하세요");
	}



}
