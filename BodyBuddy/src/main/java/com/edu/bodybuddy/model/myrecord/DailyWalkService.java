package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.DailyWalk;

public interface DailyWalkService {
	public List selectAllDailyWalkForDay();
	public List selectAllDailyWalkForWeek();
	public List selectAllDailyWalkForMonth();
	public void regist(DailyWalk dailyWalk);
	public void delete(DailyWalk dailyWalk);
}
