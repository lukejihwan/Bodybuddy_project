package com.edu.bodybuddy.model.myrecord;

import com.edu.bodybuddy.domain.myrecord.ExrDetailRecord;

public interface ExrDetailRecordDAO {
	//운동기록 등록시 운동상세기록이 수행될 insert문
	//운동기록 수정시 운동상세기록 수정이 수행될 insert문
	public void insert(ExrDetailRecord exrDetailRecord);
	
	//운동기록 수정시 운동상세기록 수정이 수행될 delete문
	public void delete(int exr_record_idx);
}
