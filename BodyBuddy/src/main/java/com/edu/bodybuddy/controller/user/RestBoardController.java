package com.edu.bodybuddy.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.board.FreeBoard;
import com.edu.bodybuddy.model.board.BoardService;
import com.edu.bodybuddy.util.Message;

@RestController
@RequestMapping("/rest/board")
public class RestBoardController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("freeBoardService")
	private BoardService freeBoardService;
	
	@PutMapping("/freeBoard")
	public ResponseEntity<Message> update(HttpServletRequest request, @RequestBody FreeBoard freeBoard){ 
		
		//3단계
		//logger.info("update : " + freeBoard);
		freeBoardService.update(freeBoard);
		
		Message message = new Message("수정 완료", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
	
	@PutMapping("/freeBoard/recommend")
	public ResponseEntity<Message> recommend(HttpServletRequest request, @RequestBody FreeBoard freeBoard){ 
		
		//3단계
		//logger.info("update : " + freeBoard);
		//나중에 멤버당 한번만 추천 가능하게 만들기
		freeBoardService.addRecommend(freeBoard.getFree_board_idx());
		
		Message message = new Message("추천 완료", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
}
