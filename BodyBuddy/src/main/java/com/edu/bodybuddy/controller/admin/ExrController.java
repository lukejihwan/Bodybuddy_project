package com.edu.bodybuddy.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.model.exr.ExrCategoryService;
import com.edu.bodybuddy.model.exr.ExrNoticeService;
@Controller
public class ExrController {
	@Autowired
	private ExrCategoryService exrCategoryService;
	@Autowired
	private ExrNoticeService exrNoticeService;
	
	
	/*-------------------------------------
	  운동 정보 (ExrNotice) 와 관련된 메서드들
	 --------------------------------------*/ 
	// 등록 페이지
	@GetMapping("/exr/notice")
	public ModelAndView getExrNotice(HttpServletRequest request) {
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		
		ModelAndView mv=new ModelAndView("admin/exr/registform");
		mv.addObject("exrCategoryList", exrCategoryList);
		return mv;
	}	
	
	
	// 목록페이지
	@GetMapping("/exr/notice/list")
	public ModelAndView getList(HttpServletRequest request) {
		List<ExrCategory> exrNoticeList=exrNoticeService.selectAll();
		
		ModelAndView mv=new ModelAndView("admin/exr/list");
		mv.addObject("exrNoticeList", exrNoticeList);
		return mv;
	}	
	
	
	// 상세페이지
	@GetMapping("/exr/notice/detail")
	public ModelAndView getDetail(HttpServletRequest request, int exr_notice_idx) {	
		ExrNotice exrNotice=exrNoticeService.select(exr_notice_idx);
		
		ModelAndView mv=new ModelAndView("admin/exr/detail");
		mv.addObject("exrNotice", exrNotice);
		return mv;
	}
	
	
	
	
	
	
}
