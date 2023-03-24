package com.edu.bodybuddy.model.myrecord;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.util.WeatherAPIManager;

@Service
public class MyRecordServiceImpl implements MyRecordService{
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WeatherAPIManager weatherAPIManager;
	
	@Override
	public Map<String, String> getWeather(int nx, int ny) {
		Map<String, String> dataForResponseMap=null;
		try {
			dataForResponseMap=weatherAPIManager.getWeatherResponse(nx, ny);
			logger.info("받아온 최종 온도는 "+dataForResponseMap.get("tempData")); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataForResponseMap;
	}

}
