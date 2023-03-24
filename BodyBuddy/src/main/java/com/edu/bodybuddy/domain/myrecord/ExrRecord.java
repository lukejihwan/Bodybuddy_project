package com.edu.bodybuddy.domain.myrecord;

import java.util.List;

import lombok.Data;

@Data
public class ExrRecord {
	private int exr_record_idx;
	private int member_idx; //신체, 운동, 식단 기록을 가져올 member_idx
	private String exr_category;
	private String exr_name;
	private int sets;
	private String regdate;
	
	//운동기록이 ExrDetailRecord를 여러개 가지고 있는 형태
	private List<ExrDetailRecord> exrRecordDetailList;

	private List<GpsData> gpsDataList;
}
