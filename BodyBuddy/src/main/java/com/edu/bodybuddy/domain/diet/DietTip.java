package com.edu.bodybuddy.domain.diet;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class DietTip {	
	private int diet_tip_idx;
	private String title;
	private String content;
	private String regdate;
	private int hit;
	private int recommend;
}
