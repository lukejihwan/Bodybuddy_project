package com.edu.bodybuddy.domain.diet;

import java.util.List;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class DietShare {
	private DietCategory dietCategory;
	private Member member;

	private int diet_share_idx;
	private String title;
	private String writer;
	private String content;
	private String preview;
	private String regdate;
	private int recommend;
	private int hit;
	private int kcal;	
	private int carbohydrate;
	private int protein;
	private int province;
	
	private List commentList;
}
