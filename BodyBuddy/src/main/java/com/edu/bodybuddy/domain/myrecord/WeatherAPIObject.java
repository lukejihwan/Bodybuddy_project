package com.edu.bodybuddy.domain.myrecord;

import lombok.Data;

@Data
public class WeatherAPIObject {
	private ResponseHeader header;
	private ResponseBody body;
	
}
