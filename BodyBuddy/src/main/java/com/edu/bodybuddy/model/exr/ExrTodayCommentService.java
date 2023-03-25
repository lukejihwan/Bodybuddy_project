package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrTodayComment;

public interface ExrTodayCommentService {
	
	public List selectAllByToday(int exr_today_idx);
	public void insert(ExrTodayComment exrTodayComment);
	public void delete(int exr_today_comment_idx);
	
	// 댓글 관련 (transaction)
	public void registReply(ExrTodayComment exrTodayComment);
}
