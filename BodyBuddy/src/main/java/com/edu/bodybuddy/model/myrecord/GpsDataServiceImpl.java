package com.edu.bodybuddy.model.myrecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.myrecord.GpsData;
import com.edu.bodybuddy.exception.GpsDataException;

@Service
public class GpsDataServiceImpl implements GpsDataService{
	@Autowired
	private GpsDataDAO gpsDataDAO;


	@Override
	public List selectForDay(String regdate) {
		return gpsDataDAO.selectForDay(regdate);
	}

	@Override
	public void insert(GpsData gpsData) throws GpsDataException{
		gpsDataDAO.insert(gpsData);
	}

	@Override
	public void delete(int exr_routine_idx) throws GpsDataException{
		gpsDataDAO.delete(exr_routine_idx);
	}


}
