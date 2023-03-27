package com.edu.bodybuddy.domain.exr;

import java.util.List;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class ExrToday {
	private int exr_today_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int recommend;
	private int hit;
	
	// fk
	private Member member;
	private List<ExrTodayComment> commentList;
}
