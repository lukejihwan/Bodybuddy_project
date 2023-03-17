package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.exr.ExrRoutineComment;
import com.edu.bodybuddy.exception.ExrRoutineCommentException;

@Service
public class ExrRoutineCommentServiceImpl implements ExrRoutineCommentService{
	@Autowired
	private ExrRoutineCommentDAO exrRoutineCommentDAO;
	
	@Override
	public List selectByFk(int exr_routine_idx) {
		return exrRoutineCommentDAO.selectByFk(exr_routine_idx);
	}

	@Override
	public void insert(ExrRoutineComment exrRoutineComment) throws ExrRoutineCommentException{
		exrRoutineCommentDAO.insert(exrRoutineComment);
	}

	@Override
	public void delete(int exr_routine_comment_idx) throws ExrRoutineCommentException{
		exrRoutineCommentDAO.delete(exr_routine_comment_idx);
	}

	@Override
	public void registReply(ExrRoutineComment exrRoutineComment) throws ExrRoutineCommentException{
		
		// 자리 마련
		if(exrRoutineComment.getStep()!=0) {
			exrRoutineCommentDAO.updateStep(exrRoutineComment);
		}
 		
		
		// 답글 등록
		exrRoutineCommentDAO.reply(exrRoutineComment);
	}


}
