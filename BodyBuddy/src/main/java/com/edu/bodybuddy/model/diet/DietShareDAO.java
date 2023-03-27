package com.edu.bodybuddy.model.diet;

import java.util.HashMap;
import java.util.List;

import com.edu.bodybuddy.domain.diet.DietShare;

public interface DietShareDAO {
	public List selectAll();
	public DietShare select(int diet_share_idx);
	public List selectByIdx(int diet_category_idx); //카테고리별 선택
	public void insert(DietShare dietShare);
	public void update(DietShare dietShare);
	public void delete(int diet_share_idx);
	
	public void addHit(int diet_share_idx); //조회수 추가 
	public void addRecommend(int diet_share_idx); //추천수 증가 
	
	public List selectBySearch(HashMap<String, String> map); //검색기능 
	
	public int totalCount(); //페이징처리
	public List selectAllPage(int page); //페이징처리
}
