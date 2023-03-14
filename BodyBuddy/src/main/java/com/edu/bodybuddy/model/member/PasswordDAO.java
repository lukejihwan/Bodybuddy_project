package com.edu.bodybuddy.model.member;

import com.edu.bodybuddy.domain.member.Member;

public interface PasswordDAO {
	public void insert(Member member);
	public void update(Member member);
}
