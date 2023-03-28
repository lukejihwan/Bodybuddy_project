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
	public Member selectByNickname(Member member) {
		return sqlSessionTemplate.selectOne("Member.selectByNickname", member);
	}
	
	@Override
	public Member selectByEmail(Member member) {
		return sqlSessionTemplate.selectOne("Member.selectByEmail", member);
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
	public void delete(Member member) throws MemberException{
		int result = sqlSessionTemplate.delete("Member.delete", member);
		if(result<1) throw new MemberException("회원 탈퇴에 실패했습니다. 관리자에게 문의하세요");
	}
}
