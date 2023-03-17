package com.edu.bodybuddy.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.diet.DietInfo;
import com.edu.bodybuddy.model.diet.DietInfoService;

@Controller
@RequestMapping("/diet")
public class DietController {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DietInfoService dietInfoService;
	
	/*--------------------------------
	 			식단정보페이지
	--------------------------------*/
	//식단정보 메인페이지
	@GetMapping("/info")
	public ModelAndView infoMain() {
		ModelAndView mav= new ModelAndView("diet/info");
		return mav;
	}
	
	//식단정보_일반식페이지
	@GetMapping("/info_general")
	public ModelAndView getGeneral() {
		ModelAndView mav= new ModelAndView("diet/info_general");
		return mav;
	}
	
	//식단정보_키토제닉페이지
	@GetMapping("/info_kito")
	public ModelAndView getKito() {
		ModelAndView mav= new ModelAndView("diet/info_kito");
		return mav;
	}
	
	//식단정보_지중해식페이지
	@GetMapping("/info_fish")
	public ModelAndView getFish() {
		ModelAndView mav= new ModelAndView("diet/info_fish");
		return mav;
	}
	
	//식단정보_비건식페이지
	@GetMapping("/info_vegan")
	public ModelAndView getVegan() {
		ModelAndView mav= new ModelAndView("diet/info_vegan");
		return mav;
	}
	
	//식단정보_키토제닉페이지
	@GetMapping("/info_time")
	public ModelAndView getTime() {
		ModelAndView mav= new ModelAndView("diet/info_time");
		return mav;
	}
	
	//상세보기페이지
	@GetMapping("/info_detail")
	public ModelAndView getDetail(HttpServletRequest request, int diet_info_idx) {
		DietInfo dietInfo=dietInfoService.select(diet_info_idx);
		
		ModelAndView mav=new ModelAndView("diet/info_detail");
		mav.addObject("dietInfo", dietInfo);
		
		return mav;
	}
	
	
	/*--------------------------------
				식단공유페이지
	--------------------------------*/
	//식단공유 메인페이지
	@GetMapping("/share")
	public ModelAndView shareMain() {
		ModelAndView mav= new ModelAndView("diet/share");
		return mav;
	}
	
	
	/*--------------------------------
				식단팁페이지
	--------------------------------*/
	//식단공유 메인페이지
	@GetMapping("/tip")
	public ModelAndView tipMain() {
		ModelAndView mav= new ModelAndView("diet/tip");
		return mav;
	}
	
	/*--------------------------------
				칼로리페이지
	--------------------------------*/
	//식단공유 메인페이지
	@GetMapping("/kcal")
	public ModelAndView kcalMain() {
		ModelAndView mav= new ModelAndView("diet/kcal");
		return mav;
	}
	
}
