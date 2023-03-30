package com.edu.bodybuddy.model.main;

import java.util.HashMap;
import java.util.List;

public interface BoardRankDAO {
	public List selectWeeklyRankByRecommend(HashMap<String, Object> map);
	public int commentCount(HashMap<String, Object> map);
}
