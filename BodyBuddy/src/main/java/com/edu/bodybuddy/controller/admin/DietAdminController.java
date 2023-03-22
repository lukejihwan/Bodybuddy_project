package com.edu.bodybuddy.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.domain.diet.DietInfo;
import com.edu.bodybuddy.model.diet.DietCategoryService;
import com.edu.bodybuddy.model.diet.DietInfoService;


@Controller
@RequestMapping("/diet")
public class DietAdminController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DietCategoryService dietCategoryService;
	
	@Autowired 
	private DietInfoService dietInfoService;
	
	//식단등록페이지_카테고리리스트가져오기
	@GetMapping("/info_registform")
	public ModelAndView getCategoryList(HttpServletRequest request) {
		List<DietCategory> dietCategoryList=dietCategoryService.selectAll();
		
		//logger.info("나와라 야~"+dietCategoryList);
		
		ModelAndView mav=new ModelAndView("admin/diet/info_registform");
		mav.addObject("dietCategoryList", dietCategoryList);
		
		return mav;
	}
	
	//식단정보 리스트페이지_리스트가져오기
	@GetMapping("/info_list")
	public ModelAndView getList(HttpServletRequest request) {
		List<DietInfo> dietInfoList=dietInfoService.selectAll();
		
		//logger.info("정보글 "+dietInfoList);
		
		ModelAndView mav=new ModelAndView("admin/diet/info_list");
		mav.addObject("dietInfoList", dietInfoList);
		
		return mav;
	}
	
	//식단정보 상세보기 페이지
	@GetMapping("/info_detail/{diet_info_idx}")
	public ModelAndView getDetail(HttpServletRequest request, @PathVariable int diet_info_idx) {
		DietInfo dietInfo=dietInfoService.select(diet_info_idx);
		
		ModelAndView mav=new ModelAndView("admin/diet/info_detail");
		mav.addObject("dietInfo", dietInfo);
		
		return mav;
	}
}
