package com.edu.bodybuddy.model.member;

import java.util.List;

import com.edu.bodybuddy.domain.member.Member;

public interface MemberService {
	public void nicknameCheck(Member member);
	public void emailCheck(Member member);
	public Member selectByEmail(Member member);
	public List<Member> getMemberList();
	public void regist(Member member);
	public void update(Member member);
	public void delete(Member member);
}
