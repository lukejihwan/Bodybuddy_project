package com.edu.bodybuddy.model.myrecord;

import java.io.IOException;
import java.util.List;

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
	public List getWeather(int nx, int ny) {
		try {
			weatherAPIManager.getWeatherResponse(nx, ny);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
