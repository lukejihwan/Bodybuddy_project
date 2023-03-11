package com.edu.bodybuddy.model.board;

import java.util.List;

public interface BoardService {
	public List selectAll();
	public Object select(int idx);
	public List selectAllByPage(int page);
	public void insert(Object object);
	public void update(Object object);
	public void delete(int idx);
	public int totalCount();
}
