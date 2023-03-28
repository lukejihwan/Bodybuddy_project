package com.edu.bodybuddy.domain.exr;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	
	// 안드로이드에서 받아낼 사진 객체
	private MultipartFile file;
	
	
	// fk
	private Member member;
	private List<ExrTodayComment> commentList;
}
