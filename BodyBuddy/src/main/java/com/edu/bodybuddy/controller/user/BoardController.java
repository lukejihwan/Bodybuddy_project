package com.edu.bodybuddy.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.board.FreeBoard;
import com.edu.bodybuddy.exception.FreeBoardException;
import com.edu.bodybuddy.model.board.BoardService;
import com.edu.bodybuddy.util.PageManager;

@Controller
@RequestMapping("/board")
public class BoardController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("freeBoardService")
	private BoardService freeBoardService;
	
	
	//디테일
	@GetMapping("/free_detail/{free_board_idx}")
	public ModelAndView getDetail(HttpServletRequest request ,@PathVariable int free_board_idx) {
		
		//3단계
		Object board = freeBoardService.select(free_board_idx);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/free_detail");
		mav.addObject("board", board);
		
		return mav;
	}
	
	//목록
	@GetMapping("/free_list/{pg}")
	public ModelAndView getList(HttpServletRequest request, @PathVariable int pg) {
		
		//logger.info("현재 페이지는 : " + pg);
		
		//3단계
		List freeBoardList = freeBoardService.selectAllByPage(pg);
		int totalCount = freeBoardService.totalCount();
		PageManager pageManager = new PageManager();
		pageManager.init(totalCount, pg);
		//logger.info("manager는 : "+pageManager);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/free_list");
		mav.addObject("freeBoardList", freeBoardList);
		mav.addObject("pageManager", pageManager);
		return mav;
	}
	
	//등록
	@PostMapping("/free_regist")
	public ModelAndView regist(HttpServletRequest request, FreeBoard freeBoard) {
		
		//3단계
		freeBoardService.insert(freeBoard);
		
		ModelAndView mav = new ModelAndView("redirect:/board/free_list");
		return mav;
	}
	
	//수정
	@PostMapping("/free_update")
	public ModelAndView update(HttpServletRequest request, FreeBoard freeBoard) {
		logger.info("update : " + freeBoard);
		
		//3단계
		freeBoardService.update(freeBoard);
		
		ModelAndView mav = new ModelAndView("redirect:/board/free_detail/"+freeBoard.getFree_board_idx());
		return mav;
	}
	
	
	//삭제
	@GetMapping("/free_delete")
	public ModelAndView delete(HttpServletRequest request, int free_board_idx) {
		
		//3단계
		freeBoardService.delete(free_board_idx);
		logger.info("delete 성공");
		
		ModelAndView mav = new ModelAndView("redirect:/board/free_list");
		return mav;
	}
	
	@ExceptionHandler(FreeBoardException.class)
	public ModelAndView handle(RuntimeException e) {
		logger.info("예외발생 : " + e.getMessage());
		return null;
	}
}
