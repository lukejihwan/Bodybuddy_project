package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.PhysicalRecord;

public interface PhysicalRecordService {
	public void regist(PhysicalRecord physicalRecord);
	public List<PhysicalRecord> selectForMonth(Map<String, String> pysicalOneMonthPeriod);
	public PhysicalRecord select(PhysicalRecord physicalRecord);
	public void update(PhysicalRecord physicalRecord);
	public void delete(PhysicalRecord physicalRecord);
}
