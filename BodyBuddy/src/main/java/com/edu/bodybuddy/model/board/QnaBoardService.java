package com.edu.bodybuddy.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.exception.QnaBoardCommentException;
import com.edu.bodybuddy.exception.QnaBoardException;
import com.edu.bodybuddy.exception.QnaBoardException;


@Service
public class QnaBoardService implements BoardService{
	
	public static final int PAGE_SIZE = 10;

	@Autowired
	@Qualifier("mybatisQnaBoardDAO")
	private BoardDAO boardDAO;
	
	@Autowired
	@Qualifier("mybatisQnaBoardCommentDAO")
	private BoardCommentDAO boardCommentDAO;
	
	public List selectAll() {
		return boardDAO.selectAll();
	}

	public Object select(int qna_board_idx) {
		return boardDAO.select(qna_board_idx);
	}

	public void insert(Object qnaBoard) throws QnaBoardException{
		boardDAO.insert(qnaBoard);
	}

	public void update(Object qnaBoard) throws QnaBoardException{
		boardDAO.update(qnaBoard);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int qna_board_idx) throws QnaBoardException{
		try {
			boardCommentDAO.deleteAllByBoard(qna_board_idx);
		} catch (QnaBoardCommentException e) {
			throw new QnaBoardException(e.getMessage(), e);
		}
		boardDAO.delete(qna_board_idx);
	}

	public List selectAllByPage(int page) {
		
		page = page - 1;
		if(page <0) page = 0;
		return boardDAO.selectAllByPage(page * PAGE_SIZE);
	}

	public int totalCount() {
		return boardDAO.totalCount();
	}

	public void addHit(int board_idx) throws QnaBoardException{
		boardDAO.addHit(board_idx);
	}

	public void addRecommend(int board_idx) throws QnaBoardException{
		boardDAO.addRecommend(board_idx);
	}

}
