package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrTip;

public interface ExrTipService {
	public List selectAll();
	public ExrTip select(int exr_tip_idx);

	public void insert(ExrTip exrTip);
	public void update(ExrTip exrTip);
	public void delete(int exr_tip_idx);
	
	// 조회수 증가
	public void plusHit(int exr_tip_idx);
}
