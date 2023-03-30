package com.edu.bodybuddy.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.member.Address;
import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.MemberException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisAddressDAO implements AddressDAO{

	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Address selectByMember(int member_idx) {
		return sqlSessionTemplate.selectOne("Address.selectByMember", member_idx);
	}

	@Override
	public void insert(Member member) {
		int result = sqlSessionTemplate.insert("Address.insert", member);
		if(result<1) throw new MemberException("회원 등록에 실패했습니다");
	}

	@Override
	public void update(Member member) {
		int result = sqlSessionTemplate.update("Address.update", member);
		if(result<1) throw new MemberException("회원 정보 수정에 실패했습니다");
	}

	@Override
	public void delete(int member_idx) {
		int result = sqlSessionTemplate.delete("Address.delete", member_idx);
		if(result<1) throw new MemberException("회원 탈퇴에 실패했습니다");
	}

}
