package com.edu.bodybuddy.domain.exr;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ExrNotice {
	private int exr_notice_idx;
	private String title;
	private String content1;
	private String content2;
	private String regdate;
	private ExrCategory exrCategory;
	
	private MultipartFile[] photo;
	private List<ExrNoticeImg> exrNoticeImgList;
}
