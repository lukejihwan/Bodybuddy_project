package com.edu.bodybuddy.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.exr.Exr_Category;
import com.edu.bodybuddy.exception.Exr_CategoryException;
import com.edu.bodybuddy.model.exr.Exr_CategoryDAO;
import com.edu.bodybuddy.model.exr.Exr_CategoryService;

// 운동 정보 페이지 Admin ver.
@RestController
@RequestMapping("/rest")
public class RestExr_NoticeController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Exr_CategoryService exr_CategoryService; 
	
	
	@GetMapping("/exr/notice")
	public List getCategoryList() {
		List<Exr_Category> exr_CategoryList=exr_CategoryService.selectAll();
		return exr_CategoryList;
	}
	
	
	@PostMapping("/exr/notice")
	public ResponseEntity<String> insert(Exr_Category exr_Category) throws Exr_CategoryException{
		logger.info("여기서 넘어오는 카테고리 두고보자"+exr_Category);
		
		exr_CategoryService.insert(exr_Category);
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 추가 완료", HttpStatus.OK);
		return entity;
	}
	
	
	@ExceptionHandler(Exr_CategoryException.class)
	public ResponseEntity<String> handle(Exr_CategoryException e){
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 등록 중 오류 발생", HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	
}
