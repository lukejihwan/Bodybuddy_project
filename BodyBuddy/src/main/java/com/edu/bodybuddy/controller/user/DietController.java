package com.edu.bodybuddy.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.domain.diet.DietInfo;
import com.edu.bodybuddy.domain.diet.DietTip;
import com.edu.bodybuddy.model.diet.DietCategoryService;
import com.edu.bodybuddy.model.diet.DietInfoService;
import com.edu.bodybuddy.model.diet.DietTipService;

@Controller
@RequestMapping("/diet")
public class DietController {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DietCategoryService dietCategoryService;
	
	@Autowired
	private DietInfoService dietInfoService;
	
	@Autowired
	private DietTipService dietTipService;
	
	/*--------------------------------
	 			식단정보페이지
	--------------------------------*/
	//식단정보 메인페이지
	@GetMapping("/info")
	public ModelAndView infoMain() {
		List<DietCategory> dietCategoryList=dietCategoryService.selectAll();
		
		ModelAndView mav= new ModelAndView("diet/info");
		mav.addObject("dietCategoryList", dietCategoryList);
		return mav;
	}
	
	//상세보기페이지
	@GetMapping("/info_detail/{diet_info_idx}")
	public ModelAndView getInfoDetail(@PathVariable int diet_info_idx) {
		DietInfo dietInfo=dietInfoService.select(diet_info_idx);
		
		ModelAndView mav=new ModelAndView("diet/info_detail");
		mav.addObject("dietInfo", dietInfo);
		
		return mav;
	}
	
	//일반식 페이지
	@GetMapping("/info_general/{diet_category_idx}")
	public ModelAndView getGeneral(@PathVariable int diet_category_idx) {
		List<DietInfo> dietInfoList=dietInfoService.selectByIdx(diet_category_idx);
		
		ModelAndView mav= new ModelAndView("diet/info_general");
		mav.addObject("dietInfoList", dietInfoList);
		return mav;
	}
	//키토제닉 페이지
	@GetMapping("/info_kito/{diet_category_idx}")
	public ModelAndView getKito(@PathVariable int diet_category_idx) {
		List<DietInfo> dietInfoList=dietInfoService.selectByIdx(diet_category_idx);			
			
		ModelAndView mav= new ModelAndView("diet/info_kito");
		mav.addObject("dietInfoList", dietInfoList);
		return mav;
	}
	//지중해식 페이지
	@GetMapping("/info_fish/{diet_category_idx}")
	public ModelAndView getFish(@PathVariable int diet_category_idx) {
		List<DietInfo> dietInfoList=dietInfoService.selectByIdx(diet_category_idx);			
			
		ModelAndView mav= new ModelAndView("diet/info_fish");
		mav.addObject("dietInfoList", dietInfoList);
		return mav;
	}
	//비건식 페이지 
	@GetMapping("/info_vegan")
	public ModelAndView getVegan(@PathVariable int diet_category_idx) {
		List<DietInfo> dietInfoList=dietInfoService.selectByIdx(diet_category_idx);			
			
		ModelAndView mav= new ModelAndView("diet/info_vegan");
		mav.addObject("dietInfoList", dietInfoList);
		return mav;
	}
	//간헐적식단 페이지
	@GetMapping("/info_time")
	public ModelAndView getTime() {
		ModelAndView mav= new ModelAndView("diet/info_time");
		return mav;
	}
	
		
	
	/*--------------------------------
				식단공유페이지
	--------------------------------*/
	//식단공유 메인페이지
	@GetMapping("/share_list")
	public ModelAndView shareMain() {
		ModelAndView mav= new ModelAndView("diet/share_list");
		return mav;
	}
	
	
	/*--------------------------------
				식단팁페이지
	--------------------------------*/
	//메인페이지
	@GetMapping("/tip_list")
	public ModelAndView tipMain() {
		List<DietTip> dietTipList=dietTipService.selectAll();
		
		ModelAndView mav= new ModelAndView("diet/tip_list");
		mav.addObject("dietTipList", dietTipList);
		return mav;
	}	
	
	//등록페이지
	@GetMapping("/tip_registform")
	public ModelAndView getTipRegistform() {
		ModelAndView mav= new ModelAndView("diet/tip_registform");
		return mav;
	}
	
	//상세보기페이지
	@GetMapping("/tip_detail/{diet_tip_idx}")
	public ModelAndView getTipDetail(@PathVariable int diet_tip_idx) {
		DietTip dietTip=dietTipService.select(diet_tip_idx);
		
		ModelAndView mav=new ModelAndView("diet/tip_detail");
		mav.addObject("dietTip", dietTip);
			
		return mav;
	}
	
	//글수정페이지
	@GetMapping("/tip_edit/{diet_tip_idx}")
	public ModelAndView getTipEdit(@PathVariable int diet_tip_idx) {
		DietTip dietTip=dietTipService.select(diet_tip_idx);
				
		ModelAndView mav=new ModelAndView("diet/tip_edit");
		mav.addObject("dietTip", dietTip);
				
		return mav;
	}
	
	/*--------------------------------
				칼로리페이지
	--------------------------------*/
	//칼로리 메인페이지
	@GetMapping("/kcal")
	public ModelAndView kcalMain() {
		ModelAndView mav= new ModelAndView("diet/kcal");
		return mav;
	}
	
}
