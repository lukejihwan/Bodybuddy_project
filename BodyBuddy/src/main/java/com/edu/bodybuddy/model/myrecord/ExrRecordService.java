package com.edu.bodybuddy.model.myrecord;

import java.util.List;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;

public interface ExrRecordService {
	public void regist(List<ExrRecord> ExrRecordList);
	public void delete(ExrRecord exrRecord);
}
