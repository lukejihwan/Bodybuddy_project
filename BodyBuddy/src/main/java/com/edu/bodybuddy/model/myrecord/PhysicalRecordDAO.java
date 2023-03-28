package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.PhysicalRecord;

public interface PhysicalRecordDAO {
	public void insert(PhysicalRecord physicalRecord);
	public List<PhysicalRecord> selectPhysicalForMonth(Map<String,String> pysicalOneMonthPeriod);
}
