package com.edu.bodybuddy.domain.diet;

import lombok.Data;

@Data
public class DietShare {
	private int member_idx;
	private int diet_category_idx;
	
	private int diet_share_idx;
	private String title;
	private String subtitle;
	private String content;
	private String regdate;
	private int hit;
	private String kcal;
}
