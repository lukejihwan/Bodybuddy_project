package com.edu.bodybuddy.model.member;

import java.util.List;

import com.edu.bodybuddy.domain.member.Member;

public interface MemberDAO {
	public Member selectByIdPass(Member member);
	public void selectById(Member member);
	public void selectByEmail(Member member);
	public List<Member> selectAll();
	public int insert(Member member);
	public void update(Member member);
	public void updatePass(Member member);
	public void delete(int member_idx);
}
