package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrRoutineComment;

public interface ExrRoutineCommentDAO {
	
	public List selectByFk(int exr_routine_idx);
	public void insert(ExrRoutineComment exrRoutineComment);
	public void update(ExrRoutineComment exrRoutineComment);
	public void delete(int exr_routine_comment_idx);

	// 해당 글에 대한 댓글의 수 모두 가져오기
	public int totalCount(int exr_routine_idx);
	
	// 댓글 관련
	public int selectMaxStep(ExrRoutineComment exrRoutineComment);	// 스텝 조사
	public void reply(ExrRoutineComment exrRoutineComment);
	public void updateStep(ExrRoutineComment exrRoutineComment);
	
}