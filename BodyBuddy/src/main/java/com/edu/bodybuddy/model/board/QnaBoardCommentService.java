package com.edu.bodybuddy.model.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.domain.board.QnaBoardComment;
import com.edu.bodybuddy.exception.QnaBoardCommentException;


@Service
public class QnaBoardCommentService implements BoardCommentService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mybatisQnaBoardCommentDAO")
	private BoardCommentDAO boardCommentDAO;

	public List selectAllByBoard(int qna_board_idx) {
		return boardCommentDAO.selectAllByBoard(qna_board_idx);
	}

	public List selectAllByMember(int member_idx) {
		return boardCommentDAO.selectAllByMember(member_idx);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(Object object) throws QnaBoardCommentException{
		 QnaBoardComment qnaBoardComment = (QnaBoardComment)object;
		 int qna_board_idx = qnaBoardComment.getQnaBoard().getQna_board_idx();
		 
		 if(qnaBoardComment.getQna_board_comment_idx()==0) {
			 //만약 해당 게시판의 첫 댓글이라면
			 //logger.info("totalCount : " + totalCount(qnaBoardComment.getQnaBoard().getQna_board_idx()));
			 if(boardCommentDAO.totalCount(qna_board_idx) == 0) {
				 qnaBoardComment.setStep(1);
			 }else {
				 qnaBoardComment.setStep(boardCommentDAO.totalCount(qna_board_idx) + 1);
			 }
			 //qna_board_comment_idx가 0이므로 새 댓글 등록
			 boardCommentDAO.insert(qnaBoardComment);
			 qnaBoardComment.setPost(qnaBoardComment.getQna_board_comment_idx());
			 boardCommentDAO.update(qnaBoardComment);
		 }else {
			 //Qna_board_comment_idx가 0이 아니므로 대댓글 등록
//			 logger.info("comment : " + qnaBoardComment.getComment());
//			 logger.info("post : " + qnaBoardComment.getPost());
//			 logger.info("step : " + qnaBoardComment.getStep());
//			 logger.info("depth : " + qnaBoardComment.getDepth());
//			 
//			 
//			 logger.info("qna_board_idx : " + qnaBoardComment.getQnaBoard().getQna_board_idx());
//			 logger.info("is it they are last? : "+(boardCommentDAO.maxStepInDepth(qnaBoardComment)==qnaBoardComment.getStep()));
			 
			 if(boardCommentDAO.maxStepInDepth(qnaBoardComment)==qnaBoardComment.getStep()) {
				 //만약 현재 댓글이 현재 댓글의 depth의 마지막 댓글이어서 maxStepInChild을 호출할 수 없다면
				 
				 //해당 post의 가장 마지막 step 위의 모든 step을 +1 한다
				 qnaBoardComment.setStep(boardCommentDAO.maxStepInPost(qnaBoardComment));
				 boardCommentDAO.shiftAboveSteps(qnaBoardComment);
				 
				 //이후 비어진 step에 해당 댓글을 삽입한다
				 qnaBoardComment.setStep(qnaBoardComment.getStep()+1);
				 qnaBoardComment.setDepth(qnaBoardComment.getDepth() + 1);
			 }else {
				 //현재 댓글의 depth의 마지막 댓글이 아니어서 maxStepInChild를 호출할 수 있다면
				 
				//logger.info("maxStepInChild : " + boardCommentDAO.maxStepInChild(qnaBoardComment));
				int maxStepInChild = boardCommentDAO.maxStepInChild(qnaBoardComment);
				
				//해당 댓글의 자식들중 가장 아래의 step 위의 모든 step들 + 1
				qnaBoardComment.setStep(maxStepInChild);
				boardCommentDAO.shiftAboveSteps(qnaBoardComment);
				
				//이후 비어진 step에 해당 댓글을 삽입한다
				qnaBoardComment.setStep(maxStepInChild+1);
				qnaBoardComment.setDepth(qnaBoardComment.getDepth() + 1);
			 }
			 
			 //상황에 따른 step, depth 설정이 완료되었다면 대댓글을 insert
			 boardCommentDAO.insert(qnaBoardComment);
		 }
		 
	}

	public void update(Object object) throws QnaBoardCommentException{
		boardCommentDAO.update(object);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int qna_board_comment_idx) throws QnaBoardCommentException{
		
		QnaBoardComment qnaBoardComment = (QnaBoardComment)boardCommentDAO.select(qna_board_comment_idx);
		boardCommentDAO.delete(qnaBoardComment.getQna_board_comment_idx());
		boardCommentDAO.unshiftAboveSteps(qnaBoardComment);
	}

	public int totalCount(int board_idx) {
		return boardCommentDAO.totalCount(board_idx);
	}

	public Object select(int comment_idx) {
		return boardCommentDAO.select(comment_idx);
	}

	

}
