package com.edu.bodybuddy.model.member;

import java.util.List;

import com.edu.bodybuddy.domain.member.Address;
import com.edu.bodybuddy.domain.mypage.Ask;

public interface AddressDAO {
	public Address selectByMember(int member_idx);
	public void insert(Address address);
	public void update(Address address);
	public void delete(int member_idx);
}
