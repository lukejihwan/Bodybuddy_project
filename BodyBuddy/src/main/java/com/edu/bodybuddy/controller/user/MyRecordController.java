package com.edu.bodybuddy.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/myrecord")
public class MyRecordController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/addrecord")
	public ModelAndView getAddPage(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("myrecord/add_record");
		return mav;
	}
	
	@GetMapping("/physical_record")
	public ModelAndView getphysicalPage(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("myrecord/physical_record");
		return mav;
	}

	@GetMapping("/exr_record")
	public ModelAndView getExrPage(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("myrecord/exr_record");
		return mav;
	}
	
	@GetMapping("/diet_record")
	public ModelAndView getDietPage(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("myrecord/diet_record");
		return mav;
	}
	
}
