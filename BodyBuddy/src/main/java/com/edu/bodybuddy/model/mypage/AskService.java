package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.mypage.Ask;

public interface AskService {
	public int getTotal(int member_idx);
	public List<Ask> getList(Map map);
	public Ask select(int ask_idx);
	public void insert(Ask ask);
	public void update(Ask ask);
	public void delete(Ask ask);
}
