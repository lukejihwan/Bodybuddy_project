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
		 
		 if(freeBoardComment.getFree_board_comment_idx()==0) {
			 //free_board_comment_idx가 0이므로 새 댓글 등록
			 boardCommentDAO.insert(freeBoardComment);
			 freeBoardComment.setPost(freeBoardComment.getFree_board_comment_idx());
			 boardCommentDAO.update(freeBoardComment);
		 }else {
			 //Free_board_comment_idx가 0이 아니므로 대댓글 등록
		 }
	}

	public void update(Object object) {
		
	}

	public void delete(int object) {
		
	}

	

}
