package com.edu.bodybuddy.domain.board;


import java.util.List;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class QnaBoard {
	private int qna_board_idx;
	private Member member;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private int recommend;
	private String thumbnail;
	private List<QnaBoardComment> commentList;
}
