package com.edu.bodybuddy.model;

import java.util.List;

import com.edu.bodybuddy.domain.Member;

public interface MemberDAO {
	public Member selectByIdPass(Member member);
	public List<Member> selectAll();
	public void insert(Member member);
	public void update(Member member);
	public void updatePass(Member member);
	public void delete(int member_idx);
}
