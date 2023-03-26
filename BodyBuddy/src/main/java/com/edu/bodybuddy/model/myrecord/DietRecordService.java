package com.edu.bodybuddy.model.myrecord;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface DietRecordService {
	public Map<String, String> getDietAPIRecord(String foodName);
}
