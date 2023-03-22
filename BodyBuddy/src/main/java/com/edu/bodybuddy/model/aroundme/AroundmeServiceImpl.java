package com.edu.bodybuddy.model.aroundme;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.util.NaverMapAPIManager;

@Service
public class AroundmeServiceImpl implements AroundmeService{
	
	@Autowired
	private NaverMapAPIManager naverMapAPIManager;
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	public HashMap<String, Object> getPlaceByCoords(String place, String latlon) {
		logger.info("service place : "+place);
		logger.info("service latlon : "+latlon);
		
		String addr = naverMapAPIManager.getLoadAddrByCoords(latlon);
		HashMap<String, Object> map = naverMapAPIManager.searchPlaceByLoadAddr(addr, place);
		logger.info("addr : "+addr);
		logger.info("searchPlaceByLoadAddr : "+map);
		return map;
	}
	
	public HashMap<String, Object> getPlaceByAddr(String place, String addr) {
		logger.info("service place : "+place);
		logger.info("service addr : "+addr);
		
		HashMap<String, Object> map = naverMapAPIManager.searchPlaceByLoadAddr(addr, place);
		logger.info("addr : "+addr);
		logger.info("searchPlaceByLoadAddr : "+map);
		return map;
	}
}
