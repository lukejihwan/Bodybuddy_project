package com.edu.bodybuddy.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping
	public String getMypage() {
		return "mypage/mypage";
	}
	
	@GetMapping("/{page}")
	public String getSubPages(@PathVariable String page) {
		log.info("페이지명 = "+ page);
		return "mypage/"+page;
	}
}
