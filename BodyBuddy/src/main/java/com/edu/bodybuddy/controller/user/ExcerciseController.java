package com.edu.bodybuddy.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// 운동 카테고리 제어 컨트롤러
@Controller
public class ExcerciseController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	// 메인페이지
	@GetMapping("/exr")
	public ModelAndView getMain() {
		logger.info("유저 페이지 작동");
		return new ModelAndView("user/excercise/notice");
	}
}
