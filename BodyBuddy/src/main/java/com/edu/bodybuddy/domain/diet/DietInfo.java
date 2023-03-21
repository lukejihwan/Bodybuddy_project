package com.edu.bodybuddy.domain.diet;

import lombok.Data;

@Data
public class DietInfo {
	private DietCategory dietCategory;

	private int diet_info_idx;
	private String title;
	private String subtitle;
	private String content;
	private String regdate;
	private String preview;
	private int kcal;	
	private int carbohydrate;
	private int protein;
	private int province;

}
