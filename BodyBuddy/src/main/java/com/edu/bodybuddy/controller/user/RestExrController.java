package com.edu.bodybuddy.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.exr.ExrRoutine;
import com.edu.bodybuddy.exception.ExrCategoryException;
import com.edu.bodybuddy.model.exr.ExrRoutineService;
import com.edu.bodybuddy.util.Msg;

@RestController
@RequestMapping("/rest/exr")
public class RestExrController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrRoutineService exrRoutineService;

	
	/*-----------------
	 *  루틴 공유 게시판
	 * ----------------*/
	
	// 등록
	@PostMapping("/routine")
	public ResponseEntity<Msg> insert(ExrRoutine exrRoutine) throws ExrCategoryException{
		//logger.info("비동기 컨트롤러 확인요 "+exrRoutine);
		exrRoutineService.insert(exrRoutine);
		
		Msg msg=new Msg();
		msg.setMsg("루틴공유글 추가 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	// 수정
	@RequestMapping(value = "/routine", method = RequestMethod.PUT)
	public ResponseEntity<Msg> update(@RequestBody ExrRoutine exrRoutine) throws ExrCategoryException{
		logger.info("비동기 카테고리는????? "+exrRoutine.getExrCategory());
		exrRoutineService.update(exrRoutine);
		
		Msg msg=new Msg();
		msg.setMsg("루틴공유글 수정 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	

	
	
}
