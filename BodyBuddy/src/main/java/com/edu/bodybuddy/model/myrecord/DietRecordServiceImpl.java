package com.edu.bodybuddy.model.myrecord;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.util.DietAPIManager;

@Service
public class DietRecordServiceImpl implements DietRecordService{

	@Autowired
	private DietAPIManager dietAPIManager;
	
	@Override
	public Map<String, String> getDietAPIRecord(String foodName){
		Map<String, String> dietAPIRecord=null;
		try {
			dietAPIRecord = dietAPIManager.getDietAPIContent(foodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dietAPIRecord;
	}

}
