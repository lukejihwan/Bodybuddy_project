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
		
		// 댓글이 최초 입력이라면 그대로 넣고
		// 최초입력이 아니라면 스텝을 증가시킨다. (뎁스는 유지)
		// 그러기 위해선? 해당 글에 대한 댓글이 있는지 "조사" 	--> 그 수가 0이 아니라면,,,
		int idx=exrRoutineComment.getExrRoutine().getExr_routine_idx();	// 해당 글에 대한 번호
		int count=exrRoutineCommentDAO.totalCount(idx);
		
		
		// 스텝 증가 여부 따지기
		if(count==0) {
			// 해당 글에 대한 댓글의 개수가 없다면 최초로 들어간
			exrRoutineComment.setStep(0);
		}else {
			
			// 해당 원본 글에 댓글이 이미 있다면, 스텝을 마지막 댓글의 수로 세팅한다!
			exrRoutineComment.setStep(count);
			// 고정된 어떤 기준이 되는 숫자가 필요함!
		}
		
		// 수정된 스텝을 최종적으로 넣어주기!
		exrRoutineCommentDAO.insert(exrRoutineComment);
		// 고유 idx를 post로 지정하기
		exrRoutineComment.setPost(exrRoutineComment.getExr_routine_comment_idx());
		exrRoutineCommentDAO.update(exrRoutineComment);
		
	}

	@Override
	public void delete(int exr_routine_comment_idx) throws ExrRoutineCommentException{
		exrRoutineCommentDAO.delete(exr_routine_comment_idx);
	}


	
	// 3단계를 여기서 모두 거칠 예정!  -- 트랜잭션!
	@Override
	public void registReply(ExrRoutineComment exrRoutineComment) throws ExrRoutineCommentException{
		
		int post=exrRoutineComment.getPost();
		exrRoutineComment.setPost(post);
		
		// 조사 들어가기
		int maxStep=exrRoutineCommentDAO.selectMaxStep(exrRoutineComment);
		logger.info("조조조"+maxStep);
		
		exrRoutineComment.setStep(maxStep);
		exrRoutineComment.setDepth(exrRoutineComment.getDepth()+1);
		//exrRoutineCommentDAO.updateStep(exrRoutineComment);
		
		// 최종적으로 인서트
		exrRoutineCommentDAO.insert(exrRoutineComment);
		
		
	}

}
