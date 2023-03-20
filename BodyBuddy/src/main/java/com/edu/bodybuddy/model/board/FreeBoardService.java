package com.edu.bodybuddy.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.exception.FreeBoardException;

@Service
public class FreeBoardService implements BoardService{
	
	public static final int PAGE_SIZE = 10;

	@Autowired
	@Qualifier("mybatisFreeBoardDAO")
	private BoardDAO boardDAO;
	
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

	public void delete(int free_board_idx) throws FreeBoardException{
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

}
