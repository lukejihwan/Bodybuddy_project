package com.edu.bodybuddy.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/")
	public String getUserMain() {
		return "index";
	}
	
	@GetMapping("/{addresses}")
	public String getMainPages(@PathVariable String addresses) {
		log.info(addresses + "카테고리 메인페이지 호출");
		return addresses;
	}
	
	
	@GetMapping("/{addresses}/{page}")
	public String getSubPages(@PathVariable String addresses, @PathVariable String page) {
		log.info("카테고리 = "+addresses+ ", 페이지명 = "+ page);
		return addresses+"/"+page;
	}
	
	@GetMapping("chuchu")
	public String chuchu() {
		return "board/free_list";
	}
	
}
