package com.edu.bodybuddy.controller.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.model.aroundme.AroundmeService;



@RestController
@RequestMapping("/rest/aroundme")
public class RestAroundmeController {
	private HashMap<String, String> placeMap;
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@Autowired
	private AroundmeService aroundmeService;
	
	public RestAroundmeController() {
		placeMap = new HashMap<String, String>();
		placeMap.put("gym", " 헬스장");
		placeMap.put("park", " 공원");
	}
	
	@GetMapping("/coords/{place}/{latlon:.+}")
	public HashMap<String, Object> getPlaceByCoords(HttpServletRequest request, @PathVariable String place, @PathVariable String latlon) {
		
		logger.info("place : "+place);
		logger.info("latlon : "+latlon);
		//3단계
		HashMap<String, Object> map = aroundmeService.getPlaceByCoords(placeMap.get(place), latlon);
		
		return map;
	}
}
