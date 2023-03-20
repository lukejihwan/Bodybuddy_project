package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;

public interface ExrRecordService {
	public List seletForMonth(Map<String, String> oneMonthPeriod);
	public void regist(List<ExrRecord> ExrRecordList);
	public void delete(ExrRecord exrRecord);
}
