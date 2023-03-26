package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.domain.exr.ExrRoutineComment;
import com.edu.bodybuddy.domain.exr.ExrTodayComment;
import com.edu.bodybuddy.exception.ExrRoutineCommentException;
import com.edu.bodybuddy.exception.ExrTodayCommentException;

@Service
public class ExrTodayCommentServiceImpl implements ExrTodayCommentService{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrTodayCommentDAO exrTodayCommentDAO;
	
	@Override
	public List selectAllByToday(int exr_today_idx) {
		return exrTodayCommentDAO.selectAllByToday(exr_today_idx);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(ExrTodayComment exrTodayComment) throws ExrTodayCommentException{
		int fk=exrTodayComment.getExrToday().getExr_today_idx();	// 해당 글에 대한 번호
		int count=exrTodayCommentDAO.totalCount(fk);					// 해당 글에 댓글이 있는지 확인
		
		// 1-1) 스텝 증가 여부 따지기
		if(count==0) {
			// 해당 글에 대한 댓글의 개수가 없다면 최초로 들어간 댓글
			exrTodayComment.setStep(0);
		}else {
			
			// 해당 원본 글에 댓글이 이미 있다면, 스텝을 마지막 댓글의 수로 세팅한다!
			exrTodayComment.setStep(count);
		}
		
		// 수정된 스텝을 최종적으로 넣어주기!
		exrTodayCommentDAO.insert(exrTodayComment);
		
		// 1-2)고정된 어떤 기준이 되는 숫자가 필요함!
		// 고유 idx를 post로 지정하기
		exrTodayComment.setPost(exrTodayComment.getExr_today_comment_idx());		// insert 후 pk 존재
		exrTodayCommentDAO.update(exrTodayComment);
		
	}
	

	@Override
	public void delete(int exr_today_comment_idx) throws ExrRoutineCommentException{
		exrTodayCommentDAO.delete(exr_today_comment_idx);
	}


	// 대댓글 등록하기
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registReply(ExrTodayComment exrTodayComment) throws ExrRoutineCommentException{
		int post=exrTodayComment.getPost();
		exrTodayComment.setPost(post);
		
		// 조사 들어가기
		int maxStep=exrTodayCommentDAO.selectMaxStep(exrTodayComment);
		logger.info("조조조"+maxStep);
		
		exrTodayComment.setStep(maxStep);
		exrTodayComment.setDepth(exrTodayComment.getDepth()+1);
		
		// 최종적으로 인서트
		exrTodayCommentDAO.insert(exrTodayComment);
		
		
	}
	

}
