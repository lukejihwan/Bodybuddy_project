package com.edu.bodybuddy.domain.myrecord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
//필요시 사요할 것JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	private String baseDate;
	private String baseTime;
	private String category;
	private String fcstDate;
	private String fcstTime;
	private String fcstValue;
	private String nx;
	private String ny;
}
