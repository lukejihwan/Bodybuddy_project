package com.edu.bodybuddy.domain.myrecord;

import lombok.Data;

@Data
public class ExrDetailRecord {
	private int exr_record_detail_idx;
	
	private int exr_record_idx; //ExrRecord자체를 보유해야하는지 고민해보자
	
	private int set_number;
	private int kg;
	private int times;
}
