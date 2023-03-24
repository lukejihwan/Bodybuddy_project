package com.edu.bodybuddy.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edu.bodybuddy.domain.myrecord.GpsData;
import com.google.gson.Gson;

import lombok.Data;

// DTO를 담은 리스트를 받아서 제이슨 리스트로 변환하여 반환하기
// 안드로이드에서 받은 데이터와 웹에 전송할 데이터가 다르므로 중간다리 역할을 할 객체임
@Data
public class ListConverter {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private List gpsList; 
	
	public String convertJSON(List<GpsData> list){
		
		// 제이슨 형태의 리스트를 전달해줄 예정!
		Gson gson=new Gson();
		String gspList=gson.toJson(list);
		
		logger.info("보낼 제이슨 데이터 모습!"+gspList);
		
		
		return gspList;
	}
}
