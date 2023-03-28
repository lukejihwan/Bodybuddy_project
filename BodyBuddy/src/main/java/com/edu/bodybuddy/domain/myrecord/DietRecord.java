package com.edu.bodybuddy.domain.myrecord;

import lombok.Data;

@Data
public class DietRecord {
	private int diet_idx;
	private int member_idx; //신체, 운동, 식단 기록을 가져올 member_idx
	private String time_category_name;
	private String food_name;
	private String servings;
	private String kcal;
	private String carbs;
	private String protein;
	private String fat;
	private String regdate;
}
