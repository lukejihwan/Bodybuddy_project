package com.edu.bodybuddy.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.exception.FreeBoardCommentException;
import com.edu.bodybuddy.exception.FreeBoardException;

@Service
public class FreeBoardService implements BoardService{
	
	public static final int PAGE_SIZE = 10;

	@Autowired
	@Qualifier("mybatisFreeBoardDAO")
	private BoardDAO boardDAO;
	
	@Autowired
	@Qualifier("mybatisFreeBoardCommentDAO")
	private BoardCommentDAO boardCommentDAO;
	
	public List selectAll() {
		return boardDAO.selectAll();
	}

	public Object select(int free_board_idx) {
		return boardDAO.select(free_board_idx);
	}

	public void insert(Object freeBoard) throws FreeBoardException{
		boardDAO.insert(freeBoard);
	}

	public void update(Object freeBoard) throws FreeBoardException{
		boardDAO.update(freeBoard);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int free_board_idx) throws FreeBoardException{
		try {
			boardCommentDAO.deleteAllByBoard(free_board_idx);
		} catch (FreeBoardCommentException e) {
			throw new FreeBoardException(e.getMessage(), e);
		}
		boardDAO.delete(free_board_idx);
	}

	public List selectAllByPage(int page) {
		
		page = page - 1;
		if(page <0) page = 0;
		return boardDAO.selectAllByPage(page * PAGE_SIZE);
	}

	public int totalCount() {
		return boardDAO.totalCount();
	}

	public void addHit(int board_idx) throws FreeBoardException{
		boardDAO.addHit(board_idx);
	}

	public void addRecommend(int board_idx) throws FreeBoardException{
		boardDAO.addRecommend(board_idx);
	}

	public List selectAllBySearch(String value, int page) {
		page = page - 1;
		if(page <0) page = 0;
		return boardDAO.selectAllBySearch(value, page * PAGE_SIZE);
	}

	public int totalCountSearch(String value) {
		return boardDAO.totalCountSearch(value);
	}

}
