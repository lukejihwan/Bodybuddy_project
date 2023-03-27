package com.edu.bodybuddy.model.myrecord;

import java.util.List;

import com.edu.bodybuddy.domain.myrecord.GpsData;

public interface GpsDataDAO {
	public List selectForDay(String regdate);
	public void insert(GpsData gpsData);
	public void delete(int gpsdata_idx);
}
