package com.edu.bodybuddy.domain.member;

import lombok.Data;

@Data
public class Address {
	private int address_idx;
	private int member_idx;
	private String address_local;
	private String address_detail;
}
