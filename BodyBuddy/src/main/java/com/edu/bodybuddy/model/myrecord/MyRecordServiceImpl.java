package com.edu.bodybuddy.model.myrecord;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.myrecord.WeatherEntity;
import com.edu.bodybuddy.util.WeatherAPIManager;

@Service
public class MyRecordServiceImpl implements MyRecordService{
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WeatherAPIManager weatherAPIManager;
	
	@Autowired
	private WeatherRecordDAO weatherRecordDAO;
	
	@Override
	public Map<String, String> selectWeather(int nx, int ny) {
		WeatherEntity weatherEntity=new WeatherEntity();
		weatherEntity.setNx(nx);
		weatherEntity.setNy(ny);
		weatherEntity=weatherRecordDAO.select(weatherEntity);
		
		Map<String, String> weatherDataForDay=new HashMap<String, String>();
		weatherDataForDay.put("tempData", weatherEntity.getTemperature());
		weatherDataForDay.put("humidityData", weatherEntity.getHumidity());
		weatherDataForDay.put("windSpeedData", weatherEntity.getWindSpeed());
		weatherDataForDay.put("precipitationData", weatherEntity.getPrecipitation());
		
		return weatherDataForDay;
	}
	
	@Override
	public WeatherEntity getWeather(WeatherEntity weatherEntity) {
		//Map<String, String> dataForResponseMap=null;
		try {
			weatherEntity=weatherAPIManager.getWeatherResponse(weatherEntity);
			//logger.info("받아온 최종 온도는 "+dataForResponseMap.get("tempData"));
			weatherRecordDAO.insert(weatherEntity);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return weatherEntity;
	}

}
