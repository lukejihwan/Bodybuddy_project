package com.edu.bodybuddy.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.exception.ExrCategoryException;
import com.edu.bodybuddy.model.exr.ExrCategoryService;

// 운동 정보 페이지 Admin ver.
// 웹에서 접근시 /admin 붙이기
@RestController
@RequestMapping("/rest")
public class RestExrNoticeController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrCategoryService exrCategoryService; 
	
	/*-----------------------------
		운동 카테고리와 관련된 메서드!
	------------------------------*/ 
	@GetMapping("/exr/category_list")
	public List getCategoryList() {
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		return exrCategoryList;
	}
	
	
	@PostMapping("/exr/category")
	public ResponseEntity<String> insert(ExrCategory exrCategory) throws ExrCategoryException{
		logger.info("여기서 넘어오는 카테고리 두고보자"+exrCategory);
		
		//exrCategoryService.insert(exrCategory);
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 추가 완료", HttpStatus.OK);
		return entity;
	}
	
	
	
	@PutMapping("/exr/category")
	public ResponseEntity<String> edit(@RequestBody ExrCategory exrCategory) throws ExrCategoryException{
		logger.info("수정할 카테고리 잘 넘어오나요~~ "+exrCategory);
		exrCategoryService.update(exrCategory);
		
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 추가 완료", HttpStatus.OK);
		return entity;
	}
	
	

	@DeleteMapping("/exr/category/{exr_category_idx}")
	public ResponseEntity<String> del(@PathVariable("exr_category_idx") int exr_category_idx) throws ExrCategoryException{
		exrCategoryService.delete(exr_category_idx);
		
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 추가 완료", HttpStatus.OK);
		return entity;
	}
	
	
	/*-----------------------------
		운동 정보 글 입력 에 대한 메서드
	------------------------------*/ 
	@PostMapping("exr/notice")
	public ResponseEntity<String> regsit(ExrNotice exrNotice){
		logger.info("정보글 입력 요청 받음"+exrNotice);
		
		ResponseEntity<String> entity=new ResponseEntity<String>("상품 등록 완료", HttpStatus.OK);
		return entity;
	}
	
	
	@ExceptionHandler(ExrCategoryException.class)
	public ResponseEntity<String> handle(ExrCategoryException e){
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 등록 중 오류 발생", HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	
	
}
