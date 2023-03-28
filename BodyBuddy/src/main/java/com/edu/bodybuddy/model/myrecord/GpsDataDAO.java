package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edu.bodybuddy.domain.myrecord.GpsData;

public interface GpsDataDAO {
	public List selectForDay(String regdate);
	public void insert(GpsData gpsData);
	public void delete(int gpsdata_idx);
	
	//지환 영역 시작
	public List selectForMonth(Map<String, String> runningOneMonthPeriod);
}
