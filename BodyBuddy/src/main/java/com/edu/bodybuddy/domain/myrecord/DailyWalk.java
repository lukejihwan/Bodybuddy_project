package com.edu.bodybuddy.domain.myrecord;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class DailyWalk {
	private int dailywalk_idx;
	private Member member;
	private int distance;
	private String regdate;
}
