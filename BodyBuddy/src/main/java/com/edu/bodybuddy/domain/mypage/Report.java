package com.edu.bodybuddy.domain.mypage;

import com.edu.bodybuddy.domain.member.Member;
import lombok.Data;

@Data
public class Report {
	private int report_idx;
	private Member member;
	private String title;
	private String content;
	private String regdate;
	private String status;
	private ReportReply report_reply;
}
