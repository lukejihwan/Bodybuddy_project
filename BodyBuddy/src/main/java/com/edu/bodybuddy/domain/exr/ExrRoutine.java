package com.edu.bodybuddy.domain.exr;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class ExrRoutine {
	private int exr_routine_idx;
	private String title;
	private String content;
	private int recommend;
	private int hit;
	private String regdate;
	
	// fk
	private ExrCategory exrCategory;
	private Member member;
}
