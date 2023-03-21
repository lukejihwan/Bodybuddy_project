package com.edu.bodybuddy.domain.diet;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class DietTipComments {
	private Member member;
	private DietTip dietTip;
	
	private int diet_tip_comments_idx;
	private String content;
	private String writer;
	private String regdate;
	private int post;
	private int step;
	private int depth;
}
