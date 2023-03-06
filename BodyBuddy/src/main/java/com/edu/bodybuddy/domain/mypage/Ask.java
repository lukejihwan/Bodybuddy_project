package com.edu.bodybuddy.domain.mypage;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.edu.bodybuddy.domain.Member;

import lombok.Data;
@Data
public class Ask {
	private int ask_idx;
	private Member member;
	private String title;
	private String content;
	private String regdate;
	private MultipartFile[] photo;
	private List<Ask_img> imgList;
}
