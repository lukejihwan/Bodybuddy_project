package com.edu.bodybuddy.model.diet;

import java.util.List;

import com.edu.bodybuddy.domain.diet.DietInfo;

public interface DietInfoService {
	public List selectAll();
	public DietInfo select(int diet_info_idx);
	public List selectByIdx(int diet_category_idx);
	public void insert(DietInfo dietInfo);
	public void update(DietInfo dietInfo);
	public void delete(int diet_info_idx);
	
}
