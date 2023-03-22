package com.edu.bodybuddy.controller.user;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.domain.diet.DietInfo;
import com.edu.bodybuddy.domain.diet.DietTip;
import com.edu.bodybuddy.exception.DietInfoException;
import com.edu.bodybuddy.exception.DietTipException;
import com.edu.bodybuddy.model.diet.DietCategoryService;
import com.edu.bodybuddy.model.diet.DietInfoService;
import com.edu.bodybuddy.model.diet.DietTipService;
import com.edu.bodybuddy.util.Msg;

@RestController
@RequestMapping("/rest/diet")
public class RestDietController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DietCategoryService dietCategoryService;
	
	@Autowired
	private DietInfoService dietInfoService;
	
	@Autowired
	private DietTipService dietTipService;
	/*--------------------------------
			식단팁 페이지 관련 
	--------------------------------*/
	//글 목록 가져오기 
	@GetMapping("/tip_list")
	public List<DietTip> getTipList(){
		return dietTipService.selectAll();
	}	
	
	//글 등록 
	@PostMapping("/tip_regist")
	public ResponseEntity<Msg> tipRegist(DietTip dietTip){
		//logger.info("식단팁 글등록 작동중 "+dietTip);
		
		dietTipService.insert(dietTip);
	
		Msg msg=new Msg();
		msg.setMsg("글 등록 성공");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);		
		return entity;
	}
	
	//글 수정
	@PutMapping("/tip_edit")
	public ResponseEntity<Msg> tipEdit(@RequestBody DietTip dietTip) throws DietTipException{
		//logger.info("식단팁수정작동 "+dietTip);
		dietTipService.update(dietTip);
			
		Msg msg=new Msg();
		msg.setMsg("글 수정 완료");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;
	}
	
	//글 삭제
	@DeleteMapping("/tip_del/{diet_tip_idx}")
	public ResponseEntity<Msg>tipDel(@PathVariable int diet_tip_idx) throws DietTipException{
		//logger.info("식단팁삭제작동 "+ diet_tip_idx);
		dietTipService.delete(diet_tip_idx);
			
		Msg msg=new Msg();
		msg.setMsg("글 삭제 완료");
			
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;	
	}
	
	//추천수 증가 
	@GetMapping("/tip/recommend/{diet_tip_idx}")
	public ResponseEntity<Msg>addRecommend(@PathVariable int diet_tip_idx) throws DietTipException{
		dietTipService.addRecommend(diet_tip_idx);
			
		Msg msg=new Msg();
		msg.setMsg("추천 되었습니다!");
			
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;	
	}
}
