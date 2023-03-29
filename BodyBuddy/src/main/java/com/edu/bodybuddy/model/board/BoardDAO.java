package com.edu.bodybuddy.model.board;

import java.util.List;

public interface BoardDAO {
	public List selectAll();
	public Object select(int board_idx);
	public List selectAllByPage(int page);
	public int totalCount();
	public void insert(Object object);
	public void update(Object object);
	public void delete(int board_idx);
	public void addHit(int board_idx);
	public void addRecommend(int board_idx);
	public List selectAllBySearch(String value, int page);
	public int totalCountSearch(String value);
}
