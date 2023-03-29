package com.edu.bodybuddy.domain.diet;

import lombok.Data;

@Data
public class Food {
	private String DESC_KOR; //음식이름
	private String NUTR_CONT1; //열량
	private String NUTR_CONT2; //탄수화물
	private String NUTR_CONT3; //단백질
	private String NUTR_CONT4; //지방	
	private String SERVING_WT; //1회 제공량
}
