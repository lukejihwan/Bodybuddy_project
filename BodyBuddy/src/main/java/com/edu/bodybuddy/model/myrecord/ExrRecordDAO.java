package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;

public interface ExrRecordDAO {
	
	//한달간의 운동 데이터를 불러오는 메서드
	public List<ExrRecord> seletForMonth(Map<String,String> oneMonthPeriod);
	
	//하루의 하나의 운동을 입력하는 메서드
	public void insert(ExrRecord exrRecord);
	
	//하루의 하나의 운동을 삭제하는 메서드
	public void delete(ExrRecord exrRecord);
	
	//하루간의 데이터를 불러오는 메서드
	public List<ExrRecord> selectForDay(String regdate);
}
