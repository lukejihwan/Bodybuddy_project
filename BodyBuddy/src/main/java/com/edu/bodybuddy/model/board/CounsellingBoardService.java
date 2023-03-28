package com.edu.bodybuddy.model.board;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.exception.CounsellingBoardException;
import com.edu.bodybuddy.domain.board.CounsellingBoard;
import com.edu.bodybuddy.exception.CounsellingBoardCommentException;
import com.edu.bodybuddy.exception.CounsellingBoardException;


@Service
public class CounsellingBoardService implements BoardService{
	
	public static final int PAGE_SIZE = 10;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mybatisCounsellingBoardDAO")
	private BoardDAO boardDAO;
	
	@Autowired
	@Qualifier("mybatisCounsellingBoardCommentDAO")
	private BoardCommentDAO boardCommentDAO;
	
	public List selectAll() {
		return boardDAO.selectAll();
	}

	public Object select(int counselling_board_idx) {
		return boardDAO.select(counselling_board_idx);
	}

	public void insert(Object counsellingBoard) throws CounsellingBoardException{
		boardDAO.insert(counsellingBoard);
	}

	public void update(Object counsellingBoard) throws CounsellingBoardException{
		boardDAO.update(counsellingBoard);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int counselling_board_idx) throws CounsellingBoardException{
		try {
			boardCommentDAO.deleteAllByBoard(counselling_board_idx);
		} catch (CounsellingBoardCommentException e) {
			throw new CounsellingBoardException(e.getMessage(), e);
		}
		boardDAO.delete(counselling_board_idx);
	}

	public List selectAllByPage(int page) {
		
		page = page - 1;
		if(page <0) page = 0;
		List<CounsellingBoard> counsellingList = boardDAO.selectAllByPage(page * PAGE_SIZE);
		
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		int count = 1;
		for(int i =0;i<counsellingList.size();i++) {
			CounsellingBoard board = counsellingList.get(i);
			if(map.get(board.getMember().getMember_idx()) == null) {
				map.put(board.getMember().getMember_idx(), "익명"+count++);
			}
			
			board.setWriter(map.get(board.getMember().getMember_idx()));
		}
		
		return counsellingList;
	}

	public int totalCount() {
		return boardDAO.totalCount();
	}

	public void addHit(int board_idx) throws CounsellingBoardException{
		boardDAO.addHit(board_idx);
	}

	public void addRecommend(int board_idx) throws CounsellingBoardException{
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
