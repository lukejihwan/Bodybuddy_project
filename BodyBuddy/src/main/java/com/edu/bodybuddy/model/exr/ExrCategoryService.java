package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrCategory;

public interface ExrCategoryService {
	public List selectAll();
	public ExrCategory select(int exr_category_idx);
	public void insert(ExrCategory exr_category);
	public void update(ExrCategory exr_category);
	public void delete(int exr_category_idx);	
}
