package com.edu.bodybuddy.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.model.exr.ExrCategoryService;

// 창에서 접근시 /admin 붙이기
@Controller
public class AdminController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrCategoryService exrCategoryService;
	
	@GetMapping("/main")
	public ModelAndView getMain() {
		ModelAndView mv = new ModelAndView("admin/index");
		return mv;
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "admin/login/loginform";
	}
	
	
	
	/*---------------------------------
		운동 정보 입력 페이지를 보여줄 메서드
	  ----------------------------------*/ 
	@GetMapping("/exr/notice")
	public ModelAndView getExrNotice() {
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		
		ModelAndView mv=new ModelAndView("admin/exr/registform");
		mv.addObject("exrCategoryList", exrCategoryList);
		return mv;
	}	
	
	@GetMapping("/diet/info_main")
	public ModelAndView getInfoMain() {
		ModelAndView mav=new ModelAndView("admin/diet/registform");
		return mav;
	}
	
	
}
