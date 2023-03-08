package com.edu.bodybuddy.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.exception.ExrCategoryException;
import com.edu.bodybuddy.model.exr.ExrCategoryService;

// 운동 정보 페이지 Admin ver.
// 웹에서 접근시 /admin 붙이기
@RestController
@RequestMapping("rest")
public class RestExrNoticeController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrCategoryService exrCategoryService; 
	
	
	@GetMapping("/exr/category_list")
	public List getCategoryList() {
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		return exrCategoryList;
	}
	
	
	@PostMapping("/exr/category")
	public ResponseEntity<String> insert(ExrCategory exrCategory) throws ExrCategoryException{
		logger.info("여기서 넘어오는 카테고리 두고보자"+exrCategory);
		
		exrCategoryService.insert(exrCategory);
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 추가 완료", HttpStatus.OK);
		return entity;
	}
	
	
	@PutMapping("/exr/category")
	public ResponseEntity<String> edit(ExrCategory exrCategory){
		logger.info("수정할 카테고리 잘 넘어오나요~~ "+exrCategory);
		
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 추가 완료", HttpStatus.OK);
		return entity;
	}
	

	
	
	@ExceptionHandler(ExrCategoryException.class)
	public ResponseEntity<String> handle(ExrCategoryException e){
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 등록 중 오류 발생", HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	
}
