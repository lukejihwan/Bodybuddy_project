package com.edu.bodybuddy.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.model.exr.ExrCategoryService;
import com.edu.bodybuddy.model.exr.ExrNoticeService;

// 운동 카테고리 제어 컨트롤러
@Controller
public class ExcerciseController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrNoticeService exrNoticeService;
	@Autowired
	private ExrCategoryService exrCategoryService;
	
	
	// 메인페이지
	@GetMapping("/exr")
	public ModelAndView getMain() {
		List<ExrNotice> exrNoticeList=exrNoticeService.selectAll();
		ModelAndView mv= new ModelAndView("exr/notice");
		mv.addObject("exrNoticeList", exrNoticeList);
		return mv;
	}

	
	// 상세페이지
	@GetMapping("/exr/notice/{exr_notice_idx}")
	public ModelAndView getdetail(@PathVariable("exr_notice_idx") int exr_notice_idx) {
		logger.info("유저 페이지 작동");
		
		ExrNotice exrNotice=exrNoticeService.select(exr_notice_idx);
		List<ExrNotice> exrCategoryList=exrCategoryService.selectAll();
		
		ModelAndView mv= new ModelAndView("exr/notice_detail");
		mv.addObject("exrNotice", exrNotice);
		mv.addObject("exrCategoryList", exrCategoryList);
		return mv;
	}
}
