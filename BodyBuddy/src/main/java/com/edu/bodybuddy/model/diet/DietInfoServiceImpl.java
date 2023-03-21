package com.edu.bodybuddy.model.diet;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.diet.DietInfo;
import com.edu.bodybuddy.exception.DietInfoException;

@Service
public class DietInfoServiceImpl implements DietInfoService{
	
	@Autowired
	private DietInfoDAO dietInfoDAO;
	
	@Override
	public List selectAll() {
		return dietInfoDAO.selectAll();
	}

	@Override
	public DietInfo select(int diet_info_idx) {
		return dietInfoDAO.select(diet_info_idx);
	}
	
	@Override
	public List selectByIdx(int diet_category_idx) {
		return dietInfoDAO.selectByIdx(diet_category_idx);
	}
	
	@Override
	public List selectBySearch(HashMap<String, String> map) {
		return dietInfoDAO.selectBySearch(map);
	}


	@Override
	public void insert(DietInfo dietInfo) throws DietInfoException{
		dietInfoDAO.insert(dietInfo);
		
	}

	@Override
	public void update(DietInfo dietInfo) throws DietInfoException{
		dietInfoDAO.update(dietInfo);
		
	}

	@Override
	public void delete(int diet_info_idx) throws DietInfoException{
		dietInfoDAO.delete(diet_info_idx);
		
	}

	
	
	
}
