package com.edu.bodybuddy.model.myrecord;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.myrecord.DietRecord;
import com.edu.bodybuddy.exception.DietRecordException;
import com.edu.bodybuddy.util.DietAPIManager;

@Service
public class DietRecordServiceImpl implements DietRecordService{

	@Autowired
	private DietAPIManager dietAPIManager;
	
	@Autowired
	private DietRecordDAO dietRecordDAO;
	
	@Override
	public List getDietAPIRecord(String foodName){
		List dietAPIRecord=null;
		try {
			dietAPIRecord = dietAPIManager.getDietAPIContent(foodName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dietAPIRecord;
	}

	@Override
	public void regist(DietRecord dietRecord) throws DietRecordException{
		dietRecordDAO.insert(dietRecord);
	}

	@Override
	public List selectForMonth(Map<String, String> oneMonthPeriod) throws DietRecordException{
		List<DietRecord> dietRecordListMonth=dietRecordDAO.selectForMonth(oneMonthPeriod);
		return dietRecordListMonth;
	}

	@Override
	public List select(DietRecord dietRecord) {
		List dietRecordList= dietRecordDAO.select(dietRecord);
		return dietRecordList;
	}

	@Override
	public void update(DietRecord dietRecord) throws DietRecordException {
		dietRecordDAO.update(dietRecord);
	}

	@Override
	public void delete(DietRecord dietRecord) throws DietRecordException {
		dietRecordDAO.delete(dietRecord);
	}

}
