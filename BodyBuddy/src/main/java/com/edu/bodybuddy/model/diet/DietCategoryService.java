package com.edu.bodybuddy.model.diet;

import java.util.List;

import com.edu.bodybuddy.domain.diet.DietCategory;

public interface DietCategoryService {
	public List selectAll();
	public DietCategory select(int diet_category_idx);
	public void insert(DietCategory diet_category);
	public void update(DietCategory diet_category);
	public void delete(int diet_category_idx);
}
