package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.myrecord.DailyWalk;
import com.edu.bodybuddy.exception.DailyWalkException;

@Service
public class DailyWalkServiceImpl implements DailyWalkService{
	

	@Autowired
	private DailyWalkDAO dailyWalkDAO;
	
	@Override
	public List selectAllDailyWalkForDay() {
		List dailyWalkList=dailyWalkDAO.selectAllWalkRecordForDay();
		return dailyWalkList;
	}
	
	@Override
	public List selectAllDailyWalkForWeek() {
		List WeekWalkList=dailyWalkDAO.selectAllWalkRecordForWeek();
		return WeekWalkList;
	}
	
	@Override
	public List selectAllDailyWalkForMonth() {
		List MonthWalkList= dailyWalkDAO.selectAllWalkRecordForMonth();
		return MonthWalkList;
	}
	

	@Override
	public void regist(DailyWalk dailyWalk) throws DailyWalkException{
		dailyWalkDAO.insert(dailyWalk);
	}

	@Override
	public void delete(DailyWalk dailyWalk) throws DailyWalkException{
		dailyWalkDAO.delete(dailyWalk);
	}


	

	

}
