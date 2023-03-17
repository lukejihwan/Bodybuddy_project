package com.edu.bodybuddy.domain.exr;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;
@Data
public class ExrRoutineComment {
	private int exr_routine_comment_idx;
	private String writer;
	private String content;
	private String regdate;
	private int post;
	private int step;
	private int depth;
	
	private ExrRoutine exrRoutine;
	private Member member;
}
