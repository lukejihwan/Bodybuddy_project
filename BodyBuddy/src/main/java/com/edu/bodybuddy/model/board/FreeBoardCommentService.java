package com.edu.bodybuddy.model.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.domain.board.FreeBoardComment;
import com.edu.bodybuddy.exception.FreeBoardCommentException;

@Service
public class FreeBoardCommentService implements BoardCommentService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mybatisFreeBoardCommentDAO")
	private BoardCommentDAO boardCommentDAO;

	public List selectAllByBoard(int free_board_idx) {
		return boardCommentDAO.selectAllByBoard(free_board_idx);
	}

	public List selectAllByMember(int member_idx) {
		return boardCommentDAO.selectAllByMember(member_idx);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(Object object) throws FreeBoardCommentException{
		 FreeBoardComment freeBoardComment = (FreeBoardComment)object;
		 int free_board_idx = freeBoardComment.getFreeBoard().getFree_board_idx();
		 
		 if(freeBoardComment.getFree_board_comment_idx()==0) {
			 //만약 해당 게시판의 첫 댓글이라면
			 //logger.info("totalCount : " + totalCount(freeBoardComment.getFreeBoard().getFree_board_idx()));
			 if(totalCount(free_board_idx) == 0) {
				 freeBoardComment.setStep(1);
			 }else {
				 freeBoardComment.setStep(totalCount(free_board_idx) + 1);
			 }
			 //free_board_comment_idx가 0이므로 새 댓글 등록
			 boardCommentDAO.insert(freeBoardComment);
			 freeBoardComment.setPost(freeBoardComment.getFree_board_comment_idx());
			 boardCommentDAO.update(freeBoardComment);
		 }else {
			 //Free_board_comment_idx가 0이 아니므로 대댓글 등록
//			 logger.info("comment : " + freeBoardComment.getComment());
//			 logger.info("post : " + freeBoardComment.getPost());
//			 logger.info("step : " + freeBoardComment.getStep());
//			 logger.info("depth : " + freeBoardComment.getDepth());
//			 
//			 
//			 logger.info("free_board_idx : " + freeBoardComment.getFreeBoard().getFree_board_idx());
//			 logger.info("is it they are last? : "+(boardCommentDAO.maxStepInDepth(freeBoardComment)==freeBoardComment.getStep()));
			 
			 if(boardCommentDAO.maxStepInDepth(freeBoardComment)==freeBoardComment.getStep()) {
				 //만약 현재 댓글이 현재 댓글의 depth의 마지막 댓글이어서 maxStepInChild을 호출할 수 없다면
				 
				 //해당 post의 가장 마지막 step 위의 모든 step을 +1 한다
				 freeBoardComment.setStep(boardCommentDAO.maxStepInPost(freeBoardComment));
				 boardCommentDAO.shiftAboveSteps(freeBoardComment);
				 
				 //이후 비어진 step에 해당 댓글을 삽입한다
				 freeBoardComment.setStep(freeBoardComment.getStep()+1);
				 freeBoardComment.setDepth(freeBoardComment.getDepth() + 1);
			 }else {
				 //현재 댓글의 depth의 마지막 댓글이 아니어서 maxStepInChild를 호출할 수 있다면
				 
				//logger.info("maxStepInChild : " + boardCommentDAO.maxStepInChild(freeBoardComment));
				int maxStepInChild = boardCommentDAO.maxStepInChild(freeBoardComment);
				
				//해당 댓글의 자식들중 가장 아래의 step 위의 모든 step들 + 1
				freeBoardComment.setStep(maxStepInChild);
				boardCommentDAO.shiftAboveSteps(freeBoardComment);
				
				//이후 비어진 step에 해당 댓글을 삽입한다
				freeBoardComment.setStep(maxStepInChild+1);
				freeBoardComment.setDepth(freeBoardComment.getDepth() + 1);
			 }
			 
			 //상황에 따른 step, depth 설정이 완료되었다면 대댓글을 insert
			 boardCommentDAO.insert(freeBoardComment);
		 }
		 
	}

	public void update(Object object) {
		boardCommentDAO.update(object);
	}

	public void delete(int free_board_comment_idx) {
		boardCommentDAO.delete(free_board_comment_idx);
	}

	public int totalCount(int board_idx) {
		return boardCommentDAO.totalCount(board_idx);
	}

	

}
