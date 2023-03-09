package com.edu.bodybuddy.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.exr.Exr_Category;
import com.edu.bodybuddy.model.exr.Exr_CategoryService;

// 운동 카테고리 제어 컨트롤러
@RestController
@RequestMapping("/rest")
public class RestExcerciseController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Exr_CategoryService exr_CategoryService; 
	
	@GetMapping("/exr/notice")
	public String getCategoryList() {
		List<Exr_Category> exr_CategoryList=exr_CategoryService.selectAll();
		
		return "ok";
	}
	
}
