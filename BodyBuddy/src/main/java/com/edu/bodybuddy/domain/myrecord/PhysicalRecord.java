package com.edu.bodybuddy.domain.myrecord;


import lombok.Data;

@Data
public class PhysicalRecord {
	private int physical_idx;
	private int member_idx; //신체, 운동, 식단 기록을 가져올 member_idx
	private int height;
	private int weight;
	private int musclemass;
	private int bodyFat;
	private int BMI;
	private String regdate;
}
