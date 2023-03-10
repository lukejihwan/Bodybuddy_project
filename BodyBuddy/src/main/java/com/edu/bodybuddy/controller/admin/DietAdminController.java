package com.edu.bodybuddy.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DietAdminController {
	
	//식단정보등록페이지 이동
	@GetMapping("/diet/info")
	public ModelAndView getInfoRegist() {
		ModelAndView mav=new ModelAndView("admin/diet/info_registform");
		return mav;
	}
}
