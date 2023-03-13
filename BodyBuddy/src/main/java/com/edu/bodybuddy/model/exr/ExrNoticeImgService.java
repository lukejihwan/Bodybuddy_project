package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrNoticeImg;

public interface ExrNoticeImgService {
	public List selectByExrNotice(int exr_notice_idx);
	public void insert(ExrNoticeImg exrNoticeImg);
	public void delete(int exr_notice_idx);
}
