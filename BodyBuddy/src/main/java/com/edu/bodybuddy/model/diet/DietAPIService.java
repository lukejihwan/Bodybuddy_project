package com.edu.bodybuddy.model.diet;

import java.util.List;

public interface DietAPIService {
	public List getFoodApi(String foodName);
	public List getSearchFood(String foodName);
}
