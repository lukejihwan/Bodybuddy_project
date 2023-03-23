package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

public interface MyRecordService {
	//날씨API정보를 처리하는 서비스 메서드
	public Map<String, String> getWeather(int nx, int ny);
}
