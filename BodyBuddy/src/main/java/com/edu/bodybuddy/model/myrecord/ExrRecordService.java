package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;

public interface ExrRecordService {
	
	//해당월의 첫날과 마지막날을 매개변수로 넣어 해당월의 운동기록을 불러오는 메서드
	public List<ExrRecord> seletForMonth(Map<String, String> oneMonthPeriod);
	
	//해당일에 데이터를 등록하는 메서드
	public void regist(List<ExrRecord> ExrRecordList);
	
	//해당일의 운동기록을 삭제하는 메서드
	public void delete(ExrRecord exrRecord);
	
	//해당 날짜의 운동기록 한건과 세부운동기록까지 같이 가져오는 메서드
	public List<ExrRecord> selectForDay(String regdate);
}
