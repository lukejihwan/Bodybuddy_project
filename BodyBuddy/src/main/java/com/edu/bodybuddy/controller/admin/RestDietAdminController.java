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
import org.springframework.web.bind.annotation.ResponseBody;
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

	/*-------------------------
	 		식단카테고리 관련 
	--------------------------*/
	
	//목록 (모달창)
		@GetMapping("/diet/list")
		public List<DietCategory> getList(){
			//logger.info("작동");
			return dietCategoryService.selectAll();			
		}
		
		
	//카테고리 등록
	@PostMapping("/diet/category")
	public ResponseEntity<Msg>regist(DietCategory dietCategory){
		//logger.info("하고있냐아"+dietCategory);
		
		dietCategoryService.insert(dietCategory);
		
		Msg msg=new Msg();
		msg.setMsg("카테고리 등록 완료");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;
	}

	
	//카테고리 수정
	@PutMapping("/diet/category")
	public ResponseEntity<Msg> edit(@RequestBody DietCategory dietCategory) throws DietCategoryException{
		logger.info("수정작동 "+dietCategory);
		dietCategoryService.update(dietCategory);
		
		Msg msg=new Msg();
		msg.setMsg("카테고리 수정 완료");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;
	}
	
	//카테고리 삭제 
	@DeleteMapping("/diet/category/{diet_category_idx}")
	public ResponseEntity<Msg> del(@PathVariable int diet_category_idx) throws DietCategoryException{
		logger.info("삭제작동 "+ diet_category_idx);
		dietCategoryService.delete(diet_category_idx);
		
		Msg msg=new Msg();
		msg.setMsg("카테고리 삭제 완료");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;	
	}
	

	@ExceptionHandler(DietCategoryException.class)
	public ResponseEntity<Msg> handle(DietCategoryException e){
		Msg msg = new Msg();
		msg.setMsg(e.getMessage());
		
		ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
