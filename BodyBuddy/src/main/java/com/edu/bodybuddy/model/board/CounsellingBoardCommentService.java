package com.edu.bodybuddy.model.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.domain.board.CounsellingBoardComment;
import com.edu.bodybuddy.exception.CounsellingBoardCommentException;


@Service
public class CounsellingBoardCommentService implements BoardCommentService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mybatisCounsellingBoardCommentDAO")
	private BoardCommentDAO boardCommentDAO;

	public List selectAllByBoard(int counselling_board_idx) {
		return boardCommentDAO.selectAllByBoard(counselling_board_idx);
	}

	public List selectAllByMember(int member_idx) {
		return boardCommentDAO.selectAllByMember(member_idx);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(Object object) throws CounsellingBoardCommentException{
		 CounsellingBoardComment counsellingBoardComment = (CounsellingBoardComment)object;
		 int counselling_board_idx = counsellingBoardComment.getCounsellingBoard().getCounselling_board_idx();
		 
		 if(counsellingBoardComment.getCounselling_board_comment_idx()==0) {
			 //만약 해당 게시판의 첫 댓글이라면
			 //logger.info("totalCount : " + totalCount(counsellingBoardComment.getCounsellingBoard().getCounselling_board_idx()));
			 if(boardCommentDAO.totalCount(counselling_board_idx) == 0) {
				 counsellingBoardComment.setStep(1);
			 }else {
				 counsellingBoardComment.setStep(boardCommentDAO.totalCount(counselling_board_idx) + 1);
			 }
			 //counselling_board_comment_idx가 0이므로 새 댓글 등록
			 boardCommentDAO.insert(counsellingBoardComment);
			 counsellingBoardComment.setPost(counsellingBoardComment.getCounselling_board_comment_idx());
			 boardCommentDAO.update(counsellingBoardComment);
		 }else {
			 //Counselling_board_comment_idx가 0이 아니므로 대댓글 등록
//			 logger.info("comment : " + counsellingBoardComment.getComment());
//			 logger.info("post : " + counsellingBoardComment.getPost());
//			 logger.info("step : " + counsellingBoardComment.getStep());
//			 logger.info("depth : " + counsellingBoardComment.getDepth());
//			 
//			 
//			 logger.info("counselling_board_idx : " + counsellingBoardComment.getCounsellingBoard().getCounselling_board_idx());
//			 logger.info("is it they are last? : "+(boardCommentDAO.maxStepInDepth(counsellingBoardComment)==counsellingBoardComment.getStep()));
			 
			 if(boardCommentDAO.maxStepInDepth(counsellingBoardComment)==counsellingBoardComment.getStep()) {
				 //만약 현재 댓글이 현재 댓글의 depth의 마지막 댓글이어서 maxStepInChild을 호출할 수 없다면
				 
				 //해당 post의 가장 마지막 step 위의 모든 step을 +1 한다
				 counsellingBoardComment.setStep(boardCommentDAO.maxStepInPost(counsellingBoardComment));
				 boardCommentDAO.shiftAboveSteps(counsellingBoardComment);
				 
				 //이후 비어진 step에 해당 댓글을 삽입한다
				 counsellingBoardComment.setStep(counsellingBoardComment.getStep()+1);
				 counsellingBoardComment.setDepth(counsellingBoardComment.getDepth() + 1);
			 }else {
				 //현재 댓글의 depth의 마지막 댓글이 아니어서 maxStepInChild를 호출할 수 있다면
				 
				//logger.info("maxStepInChild : " + boardCommentDAO.maxStepInChild(counsellingBoardComment));
				int maxStepInChild = boardCommentDAO.maxStepInChild(counsellingBoardComment);
				
				//해당 댓글의 자식들중 가장 아래의 step 위의 모든 step들 + 1
				counsellingBoardComment.setStep(maxStepInChild);
				boardCommentDAO.shiftAboveSteps(counsellingBoardComment);
				
				//이후 비어진 step에 해당 댓글을 삽입한다
				counsellingBoardComment.setStep(maxStepInChild+1);
				counsellingBoardComment.setDepth(counsellingBoardComment.getDepth() + 1);
			 }
			 
			 //상황에 따른 step, depth 설정이 완료되었다면 대댓글을 insert
			 boardCommentDAO.insert(counsellingBoardComment);
		 }
		 
	}

	public void update(Object object) throws CounsellingBoardCommentException{
		boardCommentDAO.update(object);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int counselling_board_comment_idx) throws CounsellingBoardCommentException{
		
		CounsellingBoardComment counsellingBoardComment = (CounsellingBoardComment)boardCommentDAO.select(counselling_board_comment_idx);
		boardCommentDAO.delete(counsellingBoardComment.getCounselling_board_comment_idx());
		boardCommentDAO.unshiftAboveSteps(counsellingBoardComment);
	}

	public int totalCount(int board_idx) {
		return boardCommentDAO.totalCount(board_idx);
	}

	public Object select(int comment_idx) {
		return boardCommentDAO.select(comment_idx);
	}

	

}
