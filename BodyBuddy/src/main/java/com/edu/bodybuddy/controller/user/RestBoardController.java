package com.edu.bodybuddy.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.board.CounsellingBoard;
import com.edu.bodybuddy.domain.board.FreeBoard;
import com.edu.bodybuddy.domain.board.QnaBoard;
import com.edu.bodybuddy.exception.CounsellingBoardException;
import com.edu.bodybuddy.exception.FreeBoardCommentException;
import com.edu.bodybuddy.exception.FreeBoardException;
import com.edu.bodybuddy.exception.QnaBoardException;
import com.edu.bodybuddy.model.board.BoardService;
import com.edu.bodybuddy.util.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/rest/board")
public class RestBoardController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("freeBoardService")
	private BoardService freeBoardService;
	
	@Autowired
	@Qualifier("qnaBoardService")
	private BoardService qnaBoardService;
	
	@Autowired
	@Qualifier("counsellingBoardService")
	private BoardService counsellingBoardService;
	
	// @자유게시판 영역
	@GetMapping("/freeBoard/{free_board_idx}")
	public FreeBoard selectFree(HttpServletRequest request, @PathVariable int free_board_idx){ 
		
		//3단계
		FreeBoard freeBoard = (FreeBoard)freeBoardService.select(free_board_idx);

		
		return freeBoard;
	}
	
	@PutMapping("/freeBoard")
	public ResponseEntity<Message> updateFree(HttpServletRequest request, @RequestBody FreeBoard freeBoard){ 
		
		//3단계
		//logger.info("update : " + freeBoard);
		freeBoardService.update(freeBoard);
		
		Message message = new Message("수정 완료", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
	
	@PutMapping("/freeBoard/recommend")
	public ResponseEntity<Message> recommendFree(HttpServletRequest request, @RequestBody FreeBoard freeBoard){ 
		
		//3단계
		//logger.info("update : " + freeBoard);
		//나중에 멤버당 한번만 추천 가능하게 만들기
		freeBoardService.addRecommend(freeBoard.getFree_board_idx());
		
		Message message = new Message("추천 완료", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
	
	// @QnA게시판 영역
	@GetMapping("/qnaBoard/{qna_board_idx}")
	public QnaBoard selectQna(HttpServletRequest request, @PathVariable int qna_board_idx){ 
		
		//3단계
		QnaBoard qnaBoard = (QnaBoard)qnaBoardService.select(qna_board_idx);

		
		return qnaBoard;
	}
	
	@PutMapping("/qnaBoard")
	public ResponseEntity<Message> updateQna(HttpServletRequest request, @RequestBody QnaBoard qnaBoard){ 
		
		//3단계
		//logger.info("update : " + qnaBoard);
		qnaBoardService.update(qnaBoard);
		
		Message message = new Message("수정 완료", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
	
	@PutMapping("/qnaBoard/recommend")
	public ResponseEntity<Message> recommendQna(HttpServletRequest request, @RequestBody QnaBoard qnaBoard){ 
		
		//3단계
		//logger.info("update : " + qnaBoard);
		//나중에 멤버당 한번만 추천 가능하게 만들기
		qnaBoardService.addRecommend(qnaBoard.getQna_board_idx());
		
		Message message = new Message("추천 완료", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
	
	// @고민상담게시판 영역
	@GetMapping("/counsellingBoard/{counselling_board_idx}")
	public CounsellingBoard selectCounselling(HttpServletRequest request, @PathVariable int counselling_board_idx){ 
		
		//3단계
		CounsellingBoard counsellingBoard = (CounsellingBoard)counsellingBoardService.select(counselling_board_idx);

		
		return counsellingBoard;
	}
	
	@PutMapping("/counsellingBoard")
	public ResponseEntity<Message> updateCounselling(HttpServletRequest request, @RequestBody CounsellingBoard counsellingBoard){ 
		
		//3단계
		//logger.info("update : " + counsellingBoard);
		counsellingBoardService.update(counsellingBoard);
		
		Message message = new Message("수정 완료", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
	
	@PutMapping("/counsellingBoard/recommend")
	public ResponseEntity<Message> recommendCounselling(HttpServletRequest request, @RequestBody CounsellingBoard counsellingBoard){ 
		
		//3단계
		//logger.info("update : " + counsellingBoard);
		//나중에 멤버당 한번만 추천 가능하게 만들기
		counsellingBoardService.addRecommend(counsellingBoard.getCounselling_board_idx());
		
		Message message = new Message("추천 완료", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
	
	@ExceptionHandler(FreeBoardException.class)
	public ModelAndView handle(RuntimeException e) {
		logger.info("예외발생 : " + e.getMessage());
		ModelAndView mav = new ModelAndView("/error/error");
		mav.addObject("e", e);
		return mav;
	}
	@ExceptionHandler(QnaBoardException.class)
	public ModelAndView handle2(RuntimeException e) {
		logger.info("예외발생 : " + e.getMessage());
		ModelAndView mav = new ModelAndView("/error/error");
		mav.addObject("e", e);
		return mav;
	}
	@ExceptionHandler(CounsellingBoardException.class)
	public ModelAndView handle3(RuntimeException e) {
		logger.info("예외발생 : " + e.getMessage());
		ModelAndView mav = new ModelAndView("/error/error");
		mav.addObject("e", e);
		return mav;
	}
}
