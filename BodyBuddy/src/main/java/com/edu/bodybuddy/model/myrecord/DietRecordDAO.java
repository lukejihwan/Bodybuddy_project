package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.DietRecord;

public interface DietRecordDAO {
	public void insert(DietRecord dietRecord);
	public List selectForMonth(Map<String, String> oneMonthPeiod);
}
