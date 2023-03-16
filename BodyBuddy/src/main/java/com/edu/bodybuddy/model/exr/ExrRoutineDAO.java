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
	// 총 레코드 반환
	public int totalCount();
	public List selectAllByPage(int pg);
	
	public void insert(ExrRoutine exrRoutine);
	public void update(ExrRoutine exrRoutine);
	public void delete(int exr_routine_idx);
}
