package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrToday;

public interface ExrTodayService {
	public List selectAll();
	public ExrToday select(int exr_today_idx);

	public void insert(ExrToday exrToday);
	public void update(ExrToday exrToday);
	public void delete(int exr_today_idx);
	
	// 조회수 증가
	public void plusHit(int exr_today_idx);
	// 추천수 증가
	public void plusRecommend(int exr_today_idx);
}
