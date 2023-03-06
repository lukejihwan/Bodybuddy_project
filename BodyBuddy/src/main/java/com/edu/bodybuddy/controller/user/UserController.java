package com.edu.bodybuddy.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String getUserMain() {
		return "user/index";
	}
	
	@GetMapping("/{addresses}")
	public String getPages(@PathVariable String addresses) {
		return "user/"+addresses;
	}

}
