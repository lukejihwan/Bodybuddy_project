package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.DietRecord;

public interface DietRecordService {
	public List getDietAPIRecord(String foodName);
	public void regist(DietRecord dietRecord);
	public List selectForMonth(Map<String, String> oneMonthPeriod);
	public List select(DietRecord dietRecord);
	public void update(DietRecord dietRecord);
	public void delete(DietRecord dietRecord);
}
