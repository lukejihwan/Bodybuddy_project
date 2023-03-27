package com.edu.bodybuddy.domain.exr;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class ExrTip {
	private int exr_tip_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int recommend;
	private int hit;
	
	// fk
	private ExrCategory exrCategory;
	private Member member;
}
