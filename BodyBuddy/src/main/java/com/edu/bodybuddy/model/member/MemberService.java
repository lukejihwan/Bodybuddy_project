package com.edu.bodybuddy.model.member;

import java.util.List;

import com.edu.bodybuddy.domain.member.Member;

public interface MemberService {
	public Member selectByIdPass(Member member);
	public List<Member> getMemberList();
	public void regist(Member member);
	public void update(Member member);
	public void updatePass(Member member);
	public void delete(int member_idx);
}
