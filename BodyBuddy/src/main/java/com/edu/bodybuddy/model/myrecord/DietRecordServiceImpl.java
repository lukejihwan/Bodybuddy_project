package com.edu.bodybuddy.model.myrecord;

import java.io.IOException;
import java.util.List;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dietAPIRecord;
	}

	@Override
	public void regist(DietRecord dietRecord) throws DietRecordException{
		dietRecordDAO.insert(dietRecord);
	}

}
