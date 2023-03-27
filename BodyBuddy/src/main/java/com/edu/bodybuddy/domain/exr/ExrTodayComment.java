package com.edu.bodybuddy.domain.exr;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;
@Data
public class ExrTodayComment {
	private int exr_today_comment_idx;
	private String writer;
	private String content;
	private String regdate;
	private int post;
	private int step;
	private int depth;
	
	private ExrToday exrToday;
	private Member member;
}
