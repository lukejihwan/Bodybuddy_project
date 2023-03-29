package com.edu.bodybuddy.model.diet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.diet.DietTipComments;
import com.edu.bodybuddy.exception.DietTipCommentsException;

@Service
public class DietTipCommentsServiceImpl implements DietTipCommentsService{
	@Autowired 
	private DietTipCommentsDAO dietTipCommentsDAO;
	
	@Override
	public List selectByIdx(int diet_tip_idx) {
		return dietTipCommentsDAO.selectByIdx(diet_tip_idx);
	}

	@Override
	public void insert(DietTipComments dietTipComments) throws DietTipCommentsException{
		
		dietTipCommentsDAO.insert(dietTipComments);
	}

	@Override
	public void update(DietTipComments dietTipComments) throws DietTipCommentsException{
		dietTipCommentsDAO.update(dietTipComments);
	}

	@Override
	public void delete(int diet_tip_comments_idx) throws DietTipCommentsException{
		dietTipCommentsDAO.delete(diet_tip_comments_idx);
	}

	@Override
	public void updateByPost(DietTipComments dietTipComments) throws DietTipCommentsException{
		dietTipCommentsDAO.updateByPost(dietTipComments);
	}

	@Override
	public int totalCount(int diet_tip_idx) {
		return dietTipCommentsDAO.totalCount(diet_tip_idx);
	}

	@Override
	public int selectMaxStep(DietTipComments dietTipComments) {
		return dietTipCommentsDAO.selectMaxStep(dietTipComments);
	}

}
