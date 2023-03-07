package com.edu.bodybuddy.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("myrecord")
public class MyRecordController {

	@GetMapping("/addrecord")
	public ModelAndView getAddPage() {
		ModelAndView mav=new ModelAndView("user/myrecord/add_record");
		return mav;
	}
	
	@GetMapping("/physical_record")
	public ModelAndView getphysicalPage() {
		ModelAndView mav=new ModelAndView("user/myrecord/physical_record");
		return mav;
	}

	@GetMapping("/exr_record")
	public ModelAndView getExrPage() {
		ModelAndView mav=new ModelAndView("user/myrecord/exr_record");
		return mav;
	}
	
	@GetMapping("/diet_record")
	public ModelAndView getDietPage() {
		ModelAndView mav=new ModelAndView("user/myrecord/diet_record");
		return mav;
	}
	
}
