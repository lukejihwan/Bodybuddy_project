package com.edu.bodybuddy.model.myrecord;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.myrecord.WeatherEntity;
import com.edu.bodybuddy.exception.WeatherException;

@Repository
public class MybatisWeatherRecordDAO implements WeatherRecordDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public WeatherEntity select(WeatherEntity weatherDataForDay) throws WeatherException{
		WeatherEntity weatherEntity=sqlSessionTemplate.selectOne("WeatherRecord.select", weatherDataForDay);
		if(weatherEntity==null) throw new WeatherException("날씨정보 조회 실패");
		return weatherEntity;
	}

	@Override
	public void insert(WeatherEntity weatherDataForDay) throws WeatherException{
		int result=sqlSessionTemplate.insert("WeatherRecord.insert", weatherDataForDay);
		if(result<1) {
			throw new WeatherException("날씨정보 등록 실패");
		}
	}

	@Override
	public void update(WeatherEntity weatherDataForDay) throws WeatherException{
		int result=sqlSessionTemplate.update("WeatherRecord.update", weatherDataForDay);
		if(result<1) {
			throw new WeatherException("날씨정보 수정 실패");
		}
	}

}
