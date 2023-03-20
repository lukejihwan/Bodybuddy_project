package com.edu.bodybuddy.domain.member;

import lombok.Data;

@Data
public class Member {
	private int member_idx;
	private String nickname;
	private String email;
	private String phone;
	private String regdate;
	private int point;
	private Role role;
	private String provider;
	private Address address;
	private Password password;
}
