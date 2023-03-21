package com.edu.bodybuddy.model.diet;

import java.util.HashMap;
import java.util.List;

import com.edu.bodybuddy.domain.diet.DietInfo;

public interface DietInfoDAO {
	public List selectAll();
	public DietInfo select(int diet_info_idx);
	public List selectByIdx(int diet_category_idx); //해당 카테고리만! 
	public List selectBySearch(HashMap<String, String> map); //검색기능
	public void insert(DietInfo dietInfo);
	public void update(DietInfo dietInfo);
	public void delete(int diet_info_idx);	
}
