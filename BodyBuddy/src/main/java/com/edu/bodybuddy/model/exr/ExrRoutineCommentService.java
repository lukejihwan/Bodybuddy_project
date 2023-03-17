package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrRoutineComment;

public interface ExrRoutineCommentService {
	public List selectByFk(int exr_routine_idx);
	public void insert(ExrRoutineComment exrRoutineComment);
	public void delete(int exr_routine_comment_idx);

	// 댓글 관련
	public void registReply(ExrRoutineComment exrRoutineComment);
}
