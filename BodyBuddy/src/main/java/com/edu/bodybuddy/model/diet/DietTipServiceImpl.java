package com.edu.bodybuddy.model.diet;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.diet.DietTip;
import com.edu.bodybuddy.exception.ExrRoutineException;

@Service
public class DietTipServiceImpl implements DietTipService{

	@Autowired
	DietTipDAO dietTipDAO;
	
	
	@Override
	public List selectAll() {
		return dietTipDAO.selectAll();
	}

	@Override
	public DietTip select(int diet_tip_idx) {
		return dietTipDAO.select(diet_tip_idx);
	}

	@Override
	public void insert(DietTip dietTip) throws ExrRoutineException{
		dietTipDAO.insert(dietTip);
	}

	@Override
	public void update(DietTip dietTip) throws ExrRoutineException{
		dietTipDAO.update(dietTip);	
	}

	@Override
	public void delete(int diet_tip_idx) throws ExrRoutineException{
		dietTipDAO.delete(diet_tip_idx);		
	}

	@Override
	public void addHit(int diet_tip_idx) throws ExrRoutineException{
		dietTipDAO.addHit(diet_tip_idx);	
	}

	@Override
	public void addRecommend(int diet_tip_idx) throws ExrRoutineException{
		dietTipDAO.addRecommend(diet_tip_idx);
	}

	@Override
	public List selectBySearch(HashMap<String, String> map) {
		return dietTipDAO.selectBySearch(map);
	}

}
