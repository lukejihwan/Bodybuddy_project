package com.edu.bodybuddy.model.exr;

import java.util.HashMap;
import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.domain.exr.ExrRoutine;

public interface ExrRoutineDAO {
	public List selectAll();
	public ExrRoutine select(int exr_routine_idx);

	// 검색 기능
	public List selectBySearch(HashMap<String, String> map);

	// 총 레코드 수
	public int totalCount();
	// 페이징 처리
	public List selectAllByPage(int pg);
	// 카테고리별 조회
	public List selectByFk(int exr_category_idx);
	
	public void insert(ExrRoutine exrRoutine);
	public void update(ExrRoutine exrRoutine);
	public void delete(int exr_routine_idx);
	
	// 조회수 증가
	public void plusHit(int exr_routine_idx);
	// 추천수 증가
	public void plusRecommend(int exr_routine_idx);
}
