package com.edu.bodybuddy.model.diet;

import java.util.List;

import com.edu.bodybuddy.domain.diet.DietTipComments;

public interface DietTipCommentsService {
	public List selectByIdx(int diet_tip_idx);
	public void insert(DietTipComments dietTipComments);
	public void update(DietTipComments dietTipComments);
	public void delete(int diet_tip_comments_idx);
	
	public void updateByPost(DietTipComments dietTipComments);	
	public int totalCount(int diet_tip_idx);
	
	public int selectMaxStep(DietTipComments dietTipComments);
}
