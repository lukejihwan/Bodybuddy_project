package com.edu.bodybuddy.model.myrecord;

import java.util.Map;

import com.edu.bodybuddy.domain.myrecord.WeatherEntity;

public interface WeatherRecordDAO {
	public WeatherEntity select(WeatherEntity weatherDataForDay);
	public void insert(WeatherEntity weatherDataForDay);
	public void update(WeatherEntity weatherDataForDay);
}
