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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.exception.DietCategoryException;
import com.edu.bodybuddy.model.diet.DietCategoryService;
import com.edu.bodybuddy.util.Msg;

@RestController
@RequestMapping("/rest")
public class RestDietAdminController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DietCategoryService dietCategoryService;

	//글쓰기 요청하기
	@PostMapping("/diet/regist")
	public ResponseEntity<Msg>regist(DietCategory dietCategory){
		logger.info("하고있냐아"+dietCategory);
		
		dietCategoryService.insert(dietCategory);
		
		Msg msg=new Msg();
		msg.setMsg("등록성공");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;
	}
	
	//목록 요청하기
	@GetMapping("/diet/regist")
	public List<DietCategory> getList(){
		logger.info("작동");
		return dietCategoryService.selectAll();
	}
	

	@ExceptionHandler(DietCategoryException.class)
	public ResponseEntity<Msg> handle(DietCategoryException e){
		Msg msg = new Msg();
		msg.setMsg(e.getMessage());
		
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
