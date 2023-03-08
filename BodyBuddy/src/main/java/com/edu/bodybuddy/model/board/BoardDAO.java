package com.edu.bodybuddy.model.board;

import java.util.List;

public interface BoardDAO {
	public List selectAll();
	public Object select(int idx);
	public void insert(Object object);
	public void update(Object object);
	public void delete(int idx);
}
