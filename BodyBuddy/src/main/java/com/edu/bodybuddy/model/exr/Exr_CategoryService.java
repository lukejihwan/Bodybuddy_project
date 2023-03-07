package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.Exr_Category;

public interface Exr_CategoryService {
	public List selectAll();
	public Exr_Category select(int exr_Category_idx);
	public void insert(Exr_Category exr_Category);
	public void update(Exr_Category exr_Category);
	public void delete(int exr_Category_idx);	
}
