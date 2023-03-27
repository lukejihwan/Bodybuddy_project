package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrTodayComment;

public interface ExrTodayCommentDAO {
	
	public List selectAllByToday(int exr_today_idx);
	public void insert(ExrTodayComment exrTodayComment);
	public void update(ExrTodayComment exrTodayComment);
	public void delete(int exr_today_comment_idx);

	// 해당 글에 대한 댓글의 수 모두 가져오기
	public int totalCount(ExrTodayComment exrTodayComment);
	
	// 댓글 관련
	public int selectMaxStep(ExrTodayComment exrTodayComment);	// 스텝 조사
	public void reply(ExrTodayComment exrTodayComment);
	public void updateStep(ExrTodayComment exrTodayComment);
	
}