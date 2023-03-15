package com.edu.bodybuddy.model.board;

import java.util.List;

public interface BoardCommentDAO {
	public List selectAllByBoard(int board_idx);
	public List selectAllByMember(int member_idx);
	public void insert(Object object);
	public void update(Object object);
	public void delete(int idx);
	public int totalCount(int board_idx);
	public int maxStep(int board_idx);
	public void shiftAboveSteps(Object object);
}
