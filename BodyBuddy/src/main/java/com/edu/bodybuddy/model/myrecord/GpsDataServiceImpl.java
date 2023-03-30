package com.edu.bodybuddy.model.myrecord;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.myrecord.GpsData;
import com.edu.bodybuddy.exception.GpsDataException;

@Service
public class GpsDataServiceImpl implements GpsDataService{
	@Autowired
	private GpsDataDAO gpsDataDAO;


	@Override
	public List selectForDay(HashMap map) {
		return gpsDataDAO.selectForDay(map);
	}

	@Override
	public void insert(GpsData gpsData) throws GpsDataException{
		gpsDataDAO.insert(gpsData);
	}

	@Override
	public void delete(int exr_routine_idx) throws GpsDataException{
		gpsDataDAO.delete(exr_routine_idx);
	}
	
	/*지환 영역 시작*/
	//중복된 날짜를 제거하기 위해 set배열로 넣어줌
	@Override
	public Set selectRunningForMonth(Map<String, String> runningOneMonthPeriod) {
		List runningListForMonth=gpsDataDAO.selectForMonth(runningOneMonthPeriod);
		Set<String> gpsRegdateForMonth=new HashSet<String>();
		for(int i=0; i<runningListForMonth.size(); i++) {
			GpsData gpsData=(GpsData)runningListForMonth.get(i);
			gpsRegdateForMonth.add(gpsData.getRegdate());
		}
		//runningListForMonth.get(0);
		return gpsRegdateForMonth;
	}
	/*지환 영역 끝*/

}
