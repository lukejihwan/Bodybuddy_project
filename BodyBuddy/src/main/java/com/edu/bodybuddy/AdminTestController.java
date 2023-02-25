package com.edu.bodybuddy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminTestController {
	@GetMapping("/admin")
	public ModelAndView getMain() {
		ModelAndView mv = new ModelAndView("admin/index");
		return mv;
	}
	@GetMapping("/admin/login")
	public String getLoginPage() {
		return "admin/login/loginform";
	}
}
