package com.edu.bodybuddy.model.diet;

import java.util.HashMap;
import java.util.List;

import com.edu.bodybuddy.domain.diet.DietTip;

public interface DietTipDAO {
	public List selectAll();
	public DietTip select(int diet_tip_idx);
	public void insert(DietTip dietTip);
	public void update(DietTip dietTip);
	public void delete(int diet_tip_idx);
	
	//조회수, 찜 수 
	public void addHit(int diet_tip_idx);
	public void addRecommend(int diet_tip_idx);
	
	//검색기능 
	public List selectBySearch(HashMap<String, String> map);
	
}
