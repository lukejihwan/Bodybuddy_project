package com.edu.bodybuddy.domain.diet;

import java.util.List;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class DietTip {	
	private Member member;
	
	private int diet_tip_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private int recommend;
	
	private List commentList;
}
