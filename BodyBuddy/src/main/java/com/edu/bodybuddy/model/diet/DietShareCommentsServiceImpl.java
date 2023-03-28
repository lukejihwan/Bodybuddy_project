package com.edu.bodybuddy.model.diet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.diet.DietShareComments;
import com.edu.bodybuddy.domain.diet.DietTipComments;
import com.edu.bodybuddy.exception.DietShareCommentsException;
import com.edu.bodybuddy.exception.DietTipCommentsException;

@Service
public class DietShareCommentsServiceImpl implements DietShareCommentsService{
	@Autowired 
	private DietShareCommentsDAO dietShareCommentsDAO;
	
	@Override
	public List selectByIdx(int diet_share_idx) {
		return dietShareCommentsDAO.selectByIdx(diet_share_idx);
	}

	@Override
	public void insert(DietShareComments dietShareComments) throws DietShareCommentsException{
		
		dietShareCommentsDAO.insert(dietShareComments);
	}

	@Override
	public void update(DietShareComments dietShareComments) throws DietShareCommentsException{
		dietShareCommentsDAO.update(dietShareComments);
	}

	@Override
	public void delete(int diet_share_comments_idx) throws DietShareCommentsException{
		dietShareCommentsDAO.delete(diet_share_comments_idx);
	}

	@Override
	public void updateByPost(DietShareComments dietShareComments) throws DietShareCommentsException{
		dietShareCommentsDAO.updateByPost(dietShareComments);
	}

	@Override
	public int totalCount(int diet_share_idx) {
		return dietShareCommentsDAO.totalCount(diet_share_idx);
	}

	@Override
	public int selectMaxStep(DietShareComments dietShareComments) {
		return dietShareCommentsDAO.selectMaxStep(dietShareComments);
	}

}
