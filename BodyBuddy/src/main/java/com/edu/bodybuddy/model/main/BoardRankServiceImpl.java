package com.edu.bodybuddy.model.main;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.main.Board;

@Service
public class BoardRankServiceImpl implements BoardRankService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardRankDAO boardRankDAO;
	public static final String DAILY = "daily";
	public static final String WEEKLY = "weekly";
	public static final String MONTHLY = "monthly";
	
	public List selectAllBoardRank(String boardName, String period) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Board> rankList = null;
		
		map.put("boardName", boardName);
		
		
		if(period.equals(DAILY)) {
			
		}else if (period.equals(WEEKLY)) {
			
			 rankList = boardRankDAO.selectWeeklyRankByRecommend(map);
			 
			 for(int i =0;i<rankList.size();i++) {
				 map.put("board_idx", rankList.get(i).getBoard_idx());
				 int commentCount = boardRankDAO.commentCount(map);
				 rankList.get(i).setCommentCount(commentCount);
			 }
			
		}else if (period.equals(MONTHLY)) {
			
		}
		
		
		return rankList;
	}

}
