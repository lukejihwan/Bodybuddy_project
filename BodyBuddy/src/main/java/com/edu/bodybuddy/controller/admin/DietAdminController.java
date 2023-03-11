package com.edu.bodybuddy.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.model.diet.DietCategoryService;

@Controller
public class DietAdminController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DietCategoryService dietCategoryService;
	
	//카테고리 리스트 가져오기
	@GetMapping("/diet/info")
	public ModelAndView getList() {
		List<DietCategory> dietCategoryList=dietCategoryService.selectAll();
		
		logger.info("나와라 야~"+dietCategoryList);
		
		ModelAndView mav=new ModelAndView("admin/diet/info_registform");
		mav.addObject("dietCategoryList", dietCategoryList);
			
		return mav;
	}
}
