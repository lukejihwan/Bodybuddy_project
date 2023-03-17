package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;

public interface ExrRecordDAO {
	public List<ExrRecord> seletForMonth(Map<String,String> oneMonthPeriod);
	public void insert(ExrRecord exrRecord);
	public void delete(ExrRecord exrRecord);
}
