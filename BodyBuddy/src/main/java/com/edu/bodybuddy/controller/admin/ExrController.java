package com.edu.bodybuddy.controller.admin;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.exception.ExrNoticeException;
import com.edu.bodybuddy.model.exr.ExrCategoryService;
import com.edu.bodybuddy.model.exr.ExrNoticeImgService;
import com.edu.bodybuddy.model.exr.ExrNoticeService;
@Controller
@RequestMapping("/exr")
public class ExrController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrCategoryService exrCategoryService;
	@Autowired
	private ExrNoticeService exrNoticeService;
	@Autowired
	private ExrNoticeImgService exrNoticeImgService;
	
	
	/*-------------------------------------
	  운동 정보 (ExrNotice) 와 관련된 메서드들
	 --------------------------------------*/ 
	// 등록 페이지
	@GetMapping("/notice")
	public ModelAndView getExrNotice(HttpServletRequest request) {
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		
		ModelAndView mv=new ModelAndView("admin/exr/notice_registform");
		mv.addObject("exrCategoryList", exrCategoryList);
		return mv;
	}	
	
	
	// 목록페이지
	@GetMapping("/notice/list")
	public ModelAndView getList(HttpServletRequest request) {
		List<ExrCategory> exrNoticeList=exrNoticeService.selectAll();
		
		ModelAndView mv=new ModelAndView("admin/exr/notice_list");
		mv.addObject("exrNoticeList", exrNoticeList);
		return mv;
	}	
	
	
	// 상세페이지
	@GetMapping("/notice/detail")
	public ModelAndView getDetail(HttpServletRequest request, int exr_notice_idx) {	
		ExrNotice exrNotice=exrNoticeService.select(exr_notice_idx);
		
		ModelAndView mv=new ModelAndView("admin/exr/notice_detail");
		mv.addObject("exrNotice", exrNotice);
		return mv;
	}
	
	

	/*
	 * //정보글 수정
	 * 
	 * @PostMapping("/notice/update") public ModelAndView updateNotice(ExrNotice
	 * exrNotice, HttpServletRequest request) { MultipartFile[]
	 * photoList=exrNotice.getPhoto(); logger.info("컨트롤러에서 잘 넘어오나 확인 "+exrNotice);
	 * 
	 * ServletContext context=request.getSession().getServletContext(); String
	 * dir=context.getRealPath("/resources/data/exr/"); // 실제 디렉토리 폴더 만드는 것 잊지 말기!
	 * logger.info("사진 저장 경로 : "+dir);
	 * 
	 * //exrNoticeService.update(exrNotice); return null;//new
	 * ModelAndView("redirect:/admin/exr/notice/notice_list"); }
	 */
	
	
	// 정보글 삭제
	@GetMapping("/notice/delete")
	public ModelAndView deleteNotice(HttpServletRequest request, int exr_notice_idx) throws ExrNoticeException{
		
		// 만약 사진이 있다면
		if(exrNoticeService.selectAll().size()>1) {
			exrNoticeImgService.delete(exr_notice_idx);
			exrNoticeService.delete(exr_notice_idx);
		}else {
			exrNoticeService.delete(exr_notice_idx);
		}

		return new ModelAndView("redirect:/admin/exr/notice/notice_list");
	}
	
	
	
}
