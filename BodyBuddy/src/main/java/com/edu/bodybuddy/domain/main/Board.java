package com.edu.bodybuddy.domain.main;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;

@Data
public class Board {
	private int board_idx;
	private Member member;
	private String title;
	private String writer;
	private String Content;
	private String Regdate;
	private int hit;
	private int recommend;
	private int commentCount;
}
