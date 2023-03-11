package com.edu.bodybuddy.model.mypage;

import java.util.List;

import com.edu.bodybuddy.domain.mypage.Ask;

public interface AskDAO {
	public List<Ask> selectAll();
	public Ask select(int ask_idx);
	public void insert(Ask ask);
	public void update(Ask ask);
	public void delete(int ask_idx);
}
