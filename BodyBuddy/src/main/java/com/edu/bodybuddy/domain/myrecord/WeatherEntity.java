package com.edu.bodybuddy.domain.myrecord;

import lombok.Data;

@Data
public class WeatherEntity {
	private int weather_idx;
	private int nx;
	private int ny;
	private String temperature;
	private String humidity;
	private String windSpeed;
	private String precipitation;
	private String regdate;
}
