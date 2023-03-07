package com.edu.bodybuddy.domain.board;

import lombok.Data;

@Data
public class FreeBoard {
	private int free_board_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}
