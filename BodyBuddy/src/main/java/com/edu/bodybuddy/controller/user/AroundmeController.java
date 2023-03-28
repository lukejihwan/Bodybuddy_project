package com.edu.bodybuddy.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/aroundme")
public class AroundmeController {
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@GetMapping("/aroundme_main")
	public ModelAndView getMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/aroundme/aroundme_main");
		return mav;
	}
}
