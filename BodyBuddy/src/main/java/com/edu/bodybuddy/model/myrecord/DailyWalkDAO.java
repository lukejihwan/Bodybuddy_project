package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.DailyWalk;

public interface DailyWalkDAO {
	//일단으로 보여주는 러닝기록 랭킹 메서드
	public List selectAllWalkRecordForDay();
	//주간으로 보여주는 러닝기록 랭킹 메서드
	public List selectAllWalkRecordForWeek();
	//월간으로 보여주는 러닝기록 랭킹 메서드
	public List selectAllWalkRecordForMonth();
	//사용자의 해당일에 뛴거리를 입력하는 메서드
	public void insert(DailyWalk dailyWalk);
	//사용자가 해당일에 뛴거리를 삭제하는 메서드
	public void delete(int member_idx);
}
