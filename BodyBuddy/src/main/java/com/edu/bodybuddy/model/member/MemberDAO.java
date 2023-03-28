package com.edu.bodybuddy.model.member;

import java.util.List;

import com.edu.bodybuddy.domain.member.Member;

public interface MemberDAO {
	public Member selectByNickname(Member member);
	public Member selectByEmail(Member member);
	public List<Member> selectAll();
	public int insert(Member member);
	public void update(Member member);
	public void delete(Member member);
}
