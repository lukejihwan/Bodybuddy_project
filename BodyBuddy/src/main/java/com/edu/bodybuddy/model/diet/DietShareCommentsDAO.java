package com.edu.bodybuddy.model.diet;

import java.util.List;

import com.edu.bodybuddy.domain.diet.DietShareComments;

public interface DietShareCommentsDAO {
	public List selectByIdx(int diet_share_idx);
	public void insert(DietShareComments dietShareComments);
	public void update(DietShareComments dietShareComments);
	public void delete(int diet_share_comments_idx);
	
	public void updateByPost(DietShareComments dietShareComments);	
	public int totalCount(int diet_share_idx);
	
	public int selectMaxStep(DietShareComments dietShareComments);
}
