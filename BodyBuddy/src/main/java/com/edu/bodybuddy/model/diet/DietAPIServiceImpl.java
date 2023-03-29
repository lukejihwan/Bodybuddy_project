package com.edu.bodybuddy.model.diet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.util.DietAPI;

@Service
public class DietAPIServiceImpl implements DietAPIService{
	@Autowired
	private DietAPI dietAPI;
	
	@Override
	public List getFoodApi() {
		List foodList=null;
		try {
			foodList=dietAPI.getDietAPIList();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return foodList;
	}

	@Override
	public List getSearchFood(String foodName) {
		List foodList=null;
		try {
			foodList=dietAPI.getDietAPISearch(foodName);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return foodList;
	}
	
}
