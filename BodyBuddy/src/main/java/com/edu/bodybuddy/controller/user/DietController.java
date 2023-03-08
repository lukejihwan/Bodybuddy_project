package com.edu.bodybuddy.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DietController {
	
	//식단정보리스트페이지
	@GetMapping("/diet")
	public ModelAndView getInfo() {
		return new ModelAndView("user/diet/info_main");
	}
}
