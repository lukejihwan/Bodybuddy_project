package com.edu.bodybuddy.domain.board;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class CounsellingBoardComment {
	private int counselling_board_comment_idx;
	private Member member;
	private CounsellingBoard counsellingBoard;
	private String comment;
	private String writer;
	private String regdate;
	private int post;
	private int step;
	private int depth;
}
