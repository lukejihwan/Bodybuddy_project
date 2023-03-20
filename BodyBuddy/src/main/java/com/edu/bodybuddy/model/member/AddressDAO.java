package com.edu.bodybuddy.model.member;

import com.edu.bodybuddy.domain.member.Address;
import com.edu.bodybuddy.domain.member.Member;

public interface AddressDAO {
	public Address selectByMember(int member_idx);
	public void insert(Member member);
	public void update(Member member);
	public void delete(int member_idx);
}
