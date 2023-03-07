package com.edu.bodybuddy.model.diet_category;

import java.util.List;

import com.edu.bodybuddy.domain.diet.Diet_Category;

public interface Diet_CategoryService {
	public List selectAll();
	public Diet_Category select(int diet_category_idx);
	public void insert(Diet_Category diet_Category);
	public void update(Diet_Category diet_Category);
	public void delete(int diet_category_idx);
}
