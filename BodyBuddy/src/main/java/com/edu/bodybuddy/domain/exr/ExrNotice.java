package com.edu.bodybuddy.domain.exr;


import lombok.Data;

@Data
public class ExrNotice {
	private int exr_notice_idx;
	private String title;
	private String content;
	private String regdate;
	private ExrCategory exrCategory;
	
	//private MultipartFile[] photo;
	//private List<ExrNoticeImg> exrNoticeImgList;
}
