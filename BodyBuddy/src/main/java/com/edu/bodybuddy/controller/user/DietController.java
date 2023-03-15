package com.edu.bodybuddy.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/diet")
public class DietController {
	
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
		ModelAndView mav= new ModelAndView("diet/info_kito");
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
