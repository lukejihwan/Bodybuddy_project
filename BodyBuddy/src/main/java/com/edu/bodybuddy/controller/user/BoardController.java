package com.edu.bodybuddy.controller.user;

import java.util.ArrayList;
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

import com.edu.bodybuddy.domain.board.CounsellingBoard;
import com.edu.bodybuddy.domain.board.FreeBoard;
import com.edu.bodybuddy.domain.board.QnaBoard;
import com.edu.bodybuddy.exception.CounsellingBoardException;
import com.edu.bodybuddy.exception.FreeBoardException;
import com.edu.bodybuddy.exception.QnaBoardException;
import com.edu.bodybuddy.model.board.BoardService;
import com.edu.bodybuddy.util.PageManager;

@Controller
@RequestMapping("/board")
public class BoardController {
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
	
	@GetMapping("/free_list")
	public ModelAndView getFreeMain(HttpServletRequest request) {
		return new ModelAndView("redirect:/board/free_list/1");
	}
	
	@GetMapping("/free_registform")
	public ModelAndView getFreeRegistForm(HttpServletRequest request) {
		return new ModelAndView("/board/free_registform");
	}
	
	//디테일 보기 페이지
	@GetMapping("/free_detail_view/{free_board_idx}")
	public ModelAndView getFreeDetailView(HttpServletRequest request ,@PathVariable int free_board_idx) {
		
		//3단계
		freeBoardService.addHit(free_board_idx);
		Object board = freeBoardService.select(free_board_idx);
		
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/free_detail_view");
		mav.addObject("board", board);
		
		return mav;
	}
	
	//디테일 수정 페이지
	@GetMapping("/free_detail_edit/{free_board_idx}")
	public ModelAndView getFreeDetailEdit(HttpServletRequest request ,@PathVariable int free_board_idx) {
		
		//3단계
		Object board = freeBoardService.select(free_board_idx);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/free_detail_edit");
		mav.addObject("board", board);
		
		return mav;
	}
	
	//목록
	@GetMapping("/free_list/{pg}")
	public ModelAndView getFreeList(HttpServletRequest request, @PathVariable int pg) {
		
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
	
	@GetMapping("/free_list/search/{value}/{pg}")
	public ModelAndView getFreeListBySearch(HttpServletRequest request, @PathVariable String value, @PathVariable int pg) {
		
		logger.info("value : " + value);
		logger.info("pg : " + pg);
		
		//3단계
		List freeBoardList = freeBoardService.selectAllBySearch(value, pg);
		PageManager pageManager = new PageManager();
		int totalCountSearch = freeBoardService.totalCountSearch(value);
		pageManager.init(totalCountSearch, pg);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/free_list");
		mav.addObject("freeBoardList", freeBoardList);
		mav.addObject("pageManager", pageManager);
		return mav;
	}
	
	//등록
	@PostMapping("/free_regist")
	public ModelAndView registFree(HttpServletRequest request, FreeBoard freeBoard) {
		
		
		//3단계
		freeBoardService.insert(freeBoard);
		
		ModelAndView mav = new ModelAndView("redirect:/board/free_list");
		return mav;
	}
	
	//삭제
	@GetMapping("/free_delete")
	public ModelAndView deleteFree(HttpServletRequest request, int free_board_idx) {
		
		//3단계
		freeBoardService.delete(free_board_idx);
		logger.info("delete 성공");
		
		ModelAndView mav = new ModelAndView("redirect:/board/free_list");
		return mav;
	}
	
	// @QnA 게시판 영역
	
	@GetMapping("/qna_list")
	public ModelAndView getQnaMain(HttpServletRequest request) {
		return new ModelAndView("redirect:/board/qna_list/1");
	}
	
	@GetMapping("/qna_registform")
	public ModelAndView getQnaRegistForm(HttpServletRequest request) {
		return new ModelAndView("/board/qna_registform");
	}
	
	//디테일 보기 페이지
	@GetMapping("/qna_detail_view/{qna_board_idx}")
	public ModelAndView getQnaDetailView(HttpServletRequest request ,@PathVariable int qna_board_idx) {
		
		//3단계
		qnaBoardService.addHit(qna_board_idx);
		Object board = qnaBoardService.select(qna_board_idx);
		
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/qna_detail_view");
		mav.addObject("board", board);
		
		return mav;
	}
	
	//디테일 수정 페이지
	@GetMapping("/qna_detail_edit/{qna_board_idx}")
	public ModelAndView getQnaDetailEdit(HttpServletRequest request ,@PathVariable int qna_board_idx) {
		
		//3단계
		Object board = qnaBoardService.select(qna_board_idx);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/qna_detail_edit");
		mav.addObject("board", board);
		
		return mav;
	}
	
	//목록
	@GetMapping("/qna_list/{pg}")
	public ModelAndView getQnaList(HttpServletRequest request, @PathVariable int pg) {
		
		//logger.info("현재 페이지는 : " + pg);
		
		//3단계
		List qnaBoardList = qnaBoardService.selectAllByPage(pg);
		int totalCount = qnaBoardService.totalCount();
		PageManager pageManager = new PageManager();
		pageManager.init(totalCount, pg);
		//logger.info("manager는 : "+pageManager);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/qna_list");
		mav.addObject("qnaBoardList", qnaBoardList);
		mav.addObject("pageManager", pageManager);
		return mav;
	}
	
	@GetMapping("/qna_list/search/{value}/{pg}")
	public ModelAndView getQnaListBySearch(HttpServletRequest request, @PathVariable String value, @PathVariable int pg) {
		
		logger.info("value : " + value);
		logger.info("pg : " + pg);
		
		//3단계
		List freeBoardList = qnaBoardService.selectAllBySearch(value, pg);
		PageManager pageManager = new PageManager();
		int totalCountSearch = qnaBoardService.totalCountSearch(value);
		pageManager.init(totalCountSearch, pg);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/qna_list");
		mav.addObject("qnaBoardList", freeBoardList);
		mav.addObject("pageManager", pageManager);
		return mav;
	}
	
	//등록
	@PostMapping("/qna_regist")
	public ModelAndView registQna(HttpServletRequest request, QnaBoard qnaBoard) {
		
		
		//3단계
		qnaBoardService.insert(qnaBoard);
		
		ModelAndView mav = new ModelAndView("redirect:/board/qna_list");
		return mav;
	}
	
	//삭제
	@GetMapping("/qna_delete")
	public ModelAndView deleteQna(HttpServletRequest request, int qna_board_idx) {
		
		//3단계
		qnaBoardService.delete(qna_board_idx);
		logger.info("delete 성공");
		
		ModelAndView mav = new ModelAndView("redirect:/board/qna_list");
		return mav;
	}
	
	// @고민상담 게시판 영역
	
	@GetMapping("/counselling_list")
	public ModelAndView getCounsellingMain(HttpServletRequest request) {
		return new ModelAndView("redirect:/board/counselling_list/1");
	}
	
	@GetMapping("/counselling_registform")
	public ModelAndView getCounsellingRegistForm(HttpServletRequest request) {
		return new ModelAndView("/board/counselling_registform");
	}
	
	//디테일 보기 페이지
	@GetMapping("/counselling_detail_view/{counselling_board_idx}")
	public ModelAndView getCounsellingDetailView(HttpServletRequest request ,@PathVariable int counselling_board_idx) {
		
		//3단계
		counsellingBoardService.addHit(counselling_board_idx);
		Object board = counsellingBoardService.select(counselling_board_idx);
		
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/counselling_detail_view");
		mav.addObject("board", board);
		
		return mav;
	}
	
	//디테일 수정 페이지
	@GetMapping("/counselling_detail_edit/{counselling_board_idx}")
	public ModelAndView getCounsellingDetailEdit(HttpServletRequest request ,@PathVariable int counselling_board_idx) {
		
		//3단계
		Object board = counsellingBoardService.select(counselling_board_idx);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/counselling_detail_edit");
		mav.addObject("board", board);
		
		return mav;
	}
	
	//목록
	@GetMapping("/counselling_list/{pg}")
	public ModelAndView getCounsellingList(HttpServletRequest request, @PathVariable int pg) {
		
		//logger.info("현재 페이지는 : " + pg);
		
		//3단계
		List counsellingBoardList = counsellingBoardService.selectAllByPage(pg);
		int totalCount = counsellingBoardService.totalCount();
		PageManager pageManager = new PageManager();
		pageManager.init(totalCount, pg);
		//logger.info("manager는 : "+pageManager);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/counselling_list");
		mav.addObject("counsellingBoardList", counsellingBoardList);
		mav.addObject("pageManager", pageManager);
		return mav;
	}
	
	@GetMapping("/counselling_list/search/{value}/{pg}")
	public ModelAndView getCounsellingListBySearch(HttpServletRequest request, @PathVariable String value, @PathVariable int pg) {
		
		logger.info("value : " + value);
		logger.info("pg : " + pg);
		
		//3단계
		List freeBoardList = counsellingBoardService.selectAllBySearch(value, pg);
		PageManager pageManager = new PageManager();
		int totalCountSearch = counsellingBoardService.totalCountSearch(value);
		pageManager.init(totalCountSearch, pg);
		
		//4단계
		ModelAndView mav = new ModelAndView("/board/counselling_list");
		mav.addObject("counsellingBoardList", freeBoardList);
		mav.addObject("pageManager", pageManager);
		return mav;
	}
	
	//등록
	@PostMapping("/counselling_regist")
	public ModelAndView registCounselling(HttpServletRequest request, CounsellingBoard counsellingBoard) {
		
		
		//3단계
		counsellingBoardService.insert(counsellingBoard);
		
		ModelAndView mav = new ModelAndView("redirect:/board/counselling_list");
		return mav;
	}
	
	//삭제
	@GetMapping("/counselling_delete")
	public ModelAndView deleteCounselling(HttpServletRequest request, int counselling_board_idx) {
		
		//3단계
		counsellingBoardService.delete(counselling_board_idx);
		logger.info("delete 성공");
		
		ModelAndView mav = new ModelAndView("redirect:/board/counselling_list");
		return mav;
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
