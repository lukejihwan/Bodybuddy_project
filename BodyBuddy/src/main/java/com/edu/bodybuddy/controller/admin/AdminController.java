package com.edu.bodybuddy.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// 창에서 접근시 /admin 붙이기
@Controller
public class AdminController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/main")
	public ModelAndView getMain() {
		ModelAndView mv = new ModelAndView("admin/index");
		return mv;
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "admin/login/loginform";
	}
	
	

	@GetMapping("/exr/notice")
	public ModelAndView getExrNotice() {
		//logger.info("정보 게시판 작동");
		return new ModelAndView("admin/exr/registform");
	}
	
	
}
