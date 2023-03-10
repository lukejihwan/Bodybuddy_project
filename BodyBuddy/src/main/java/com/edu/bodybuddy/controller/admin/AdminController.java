package com.edu.bodybuddy.controller.admin;

import javax.servlet.http.HttpServletRequest;
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
	public ModelAndView getMain(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/index");
		return mv;
	}
	
	@GetMapping("/login")
	public String getLoginPage(HttpServletRequest request) {
		return "admin/login/loginform";
	}
	
	
	
	/*---------------------------------
		운동 메서드 이사감 (확인 했으면 지워도 되는 주석)
	  ----------------------------------*/ 

	
	@GetMapping("/diet/info_main")
	public ModelAndView getInfoMain(HttpServletRequest request) {
		logger.info("정보 게시판 작동");
		ModelAndView mv = new ModelAndView("admin/exr/registform");
		return mv;	
	}
	/*------------------------------------------------------------------
	  								식단게시판
	-------------------------------------------------------------------*/
	@GetMapping("/diet/info")
	public ModelAndView getInfoRegist(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("admin/diet/registform");
		return mav;
	}
	
	
}
