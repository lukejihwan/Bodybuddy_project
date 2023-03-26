package com.edu.bodybuddy.model.diet;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.diet.DietShare;

@Service
public class DietShareServiceImpl implements DietShareService{

	@Autowired
	DietShareDAO dietShareDAO;
	
	@Override
	public List selectAll() {
		return dietShareDAO.selectAll();
	}

	@Override
	public DietShare select(int diet_share_idx) {
		return dietShareDAO.select(diet_share_idx);
	}

	@Override
	public List selectByIdx(int diet_category_idx) {
		return dietShareDAO.selectByIdx(diet_category_idx);
	}

	@Override
	public void insert(DietShare dietShare) {
		dietShareDAO.insert(dietShare);
	}

	@Override
	public void update(DietShare dietShare) {
		dietShareDAO.update(dietShare);
	}

	@Override
	public void delete(int diet_share_idx) {
		dietShareDAO.delete(diet_share_idx);
	}

	@Override
	public void addHit(int diet_share_idx) {
		dietShareDAO.addHit(diet_share_idx);
	}

	@Override
	public void addRecommend(int diet_share_idx) {
		dietShareDAO.addRecommend(diet_share_idx);
	}

	@Override
	public List selectBySearch(HashMap<String, String> map) {
		return dietShareDAO.selectBySearch(map);
	}

	@Override
	public int totalCount() {
		return dietShareDAO.totalCount();
	}

	@Override
	public List selectAllPage(int page) {
		return dietShareDAO.selectAllPage(page);
	}

}
