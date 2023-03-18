package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.exr.ExrRoutineComment;
import com.edu.bodybuddy.exception.ExrRoutineCommentException;

@Service
public class ExrRoutineCommentServiceImpl implements ExrRoutineCommentService{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrRoutineCommentDAO exrRoutineCommentDAO;
	
	@Override
	public List selectByFk(int exr_routine_idx) {
		return exrRoutineCommentDAO.selectByFk(exr_routine_idx);
	}

	@Override
	public void insert(ExrRoutineComment exrRoutineComment) throws ExrRoutineCommentException{
		/*
		 * logger.info("서비스에서 확인"); logger.info(""+exrRoutineComment.getPost());
		 * logger.info(""+exrRoutineComment.getStep());
		 * logger.info(""+exrRoutineComment.getDepth());
		 */
		
		// 부모의 fk를 팀으로 세팅하기
		exrRoutineComment.setPost(exrRoutineComment.getExrRoutine().getExr_routine_idx());
		
		// 같은 팀 내에서 스템 수 구해오기
		/*
		 * int maxStep=exrRoutineCommentDAO.selectMaxStep(exrRoutineComment);
		 * logger.info(""+maxStep); // 스텝이 1보다 크면 증가 시킨 후 등록한다 if(maxStep!=0) {
		 * exrRoutineCommentDAO.updateStep(exrRoutineComment); }
		 */
		
		exrRoutineCommentDAO.insert(exrRoutineComment);
	}

	@Override
	public void delete(int exr_routine_comment_idx) throws ExrRoutineCommentException{
		exrRoutineCommentDAO.delete(exr_routine_comment_idx);
	}


	// 3단계를 여기서 모두 거칠 예정!  -- 트랜잭션!
	@Override
	public void registReply(ExrRoutineComment exrRoutineComment) throws ExrRoutineCommentException{
		
		//1 마지막 스텝 구하기
		int maxStep=exrRoutineCommentDAO.selectMaxStep(exrRoutineComment);
		logger.info("마지막 스텝은?"+maxStep);
		// 자리 마련
		/*
		 * if(exrRoutineComment.getStep()!=0) {
		 * exrRoutineCommentDAO.updateStep(exrRoutineComment); }
		 */
		
		// 답글 등록
		//exrRoutineCommentDAO.reply(exrRoutineComment);
	}

}
