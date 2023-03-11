package com.edu.bodybuddy.domain.exr;

import lombok.Data;

@Data
public class ExrNoticeImg {
	private int exr_notice_img_idx;
	private ExrNotice exrNotice;
	private String filename;
}
