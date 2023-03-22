package com.edu.bodybuddy.model.myrecord;

import java.util.List;

public interface MyRecordService {
	//날씨API정보를 처리하는 서비스 메서드
	public List getWeather(int nx, int ny);
}
