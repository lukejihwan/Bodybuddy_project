package com.edu.bodybuddy.model.myrecord;

import java.util.List;

import com.edu.bodybuddy.domain.myrecord.DietRecord;

public interface DietRecordService {
	public List getDietAPIRecord(String foodName);
	public void regist(DietRecord dietRecord);
}
