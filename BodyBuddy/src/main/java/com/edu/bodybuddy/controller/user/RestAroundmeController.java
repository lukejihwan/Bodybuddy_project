package com.edu.bodybuddy.controller.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.model.aroundme.AroundmeService;
import com.edu.bodybuddy.util.Message;
import com.edu.bodybuddy.util.PageManager;



@RestController
@RequestMapping("/rest/aroundme")
public class RestAroundmeController {
	private HashMap<String, String> placeMap;
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@Autowired
	private AroundmeService aroundmeService;
	
	public RestAroundmeController() {
		placeMap = new HashMap<String, String>();
		placeMap.put("gym", " 근처 헬스장");
		placeMap.put("park", " 근처 공원");
	}
	
	@GetMapping("/place/coords/{place}/{latlon:.+}")
	public HashMap<String, Object> getPlaceByCoords(HttpServletRequest request, @PathVariable String place, @PathVariable String latlon) {
		
		logger.info("place : "+place);
		logger.info("latlon : "+latlon);
		//3단계
		HashMap<String, Object> map = aroundmeService.getPlaceByCoords(placeMap.get(place), latlon);
		
		return map;
	}
	@GetMapping("/addr/coords/{latlon:.+}")
	public ResponseEntity<Message> getAddrByCoords(HttpServletRequest request,@PathVariable String latlon) {
		logger.info("latlon : "+latlon);
		//3단계
		String addr = aroundmeService.getAddrByCoords(latlon);
	
		Message message = new Message(addr, 200);
		ResponseEntity entity = new ResponseEntity(message, HttpStatus.OK);
		
		return entity;
	}
	@GetMapping("/coords/addr/{addr}")
	public HashMap<String, Object> getCoordsByAddr(HttpServletRequest request,@PathVariable String addr) {
		logger.info("addr : "+addr);
		//3단계
		HashMap<String, Object> map = aroundmeService.getCoordsByAddr(addr);
		logger.info("getCoordsByAddr : "+map);
		
		return map;
	}
	@GetMapping("/blog/{title}/{page}")
	public HashMap<String, Object> getBlogByTitle(HttpServletRequest request, @PathVariable String title, @PathVariable int page) {
		logger.info("title : "+title);
		logger.info("page : "+page);
		//3단계
		HashMap<String, Object> map = aroundmeService.getBlogByTitle(title, page);
		
		PageManager pageManager = new PageManager();
		pageManager.setPageSize((Integer)map.get("display"));
		pageManager.init((Integer)map.get("total"), page);
		//logger.info("page : "+pageManager);
		map.put("pageManager", pageManager);
		map.put("searchQuery", title);
		
		return map;
	}
}
