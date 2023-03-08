package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrNotice;

public interface ExrNoticeDAO {
	public List selectAll();
	public ExrNotice select(int exr_notice_idx);
	public void insert(ExrNotice exrNotice);
	public void update(ExrNotice exrNotice);
	public void delete(int exr_notice_idx);
}
