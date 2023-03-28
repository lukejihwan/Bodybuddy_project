package com.edu.bodybuddy.domain.mypage;

import com.edu.bodybuddy.domain.member.Member;

import lombok.Data;
@Data
public class Ask {
	private int ask_idx;
	private Member member;
	private String title;
	private String content;
	private String regdate;
	private String status;
	private AskReply ask_reply;
}
