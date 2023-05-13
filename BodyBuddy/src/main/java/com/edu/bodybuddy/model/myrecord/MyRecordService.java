package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.WeatherEntity;

public interface MyRecordService {
	//날씨 API정보를 조회하는 서비스 메서드
	public Map<String, String> selectWeather(int nx, int ny);
	
	//날씨API정보를 처리하는 서비스 메서드
	public WeatherEntity getWeather(WeatherEntity weatherEntity);
}
