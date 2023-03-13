package com.edu.bodybuddy.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.model.diet.DietCategoryService;

@RestController
@RequestMapping("/rest")
public class RestDietController {

	@Autowired
	private DietCategoryService dietCategoryService;
	
	@GetMapping("/diet/info")
	public List<DietCategory> getCategoryList() {
		return dietCategoryService.selectAll();	
	}
	
	@PostMapping("/diet/regist")
	public ModelAndView regist(DietCategory dietCategory) {
		dietCategoryService.insert(dietCategory);
		
		return new ModelAndView("redirect:/diet/info");
	}
}
