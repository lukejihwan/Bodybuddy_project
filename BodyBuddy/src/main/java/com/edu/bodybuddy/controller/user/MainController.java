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
	
}
