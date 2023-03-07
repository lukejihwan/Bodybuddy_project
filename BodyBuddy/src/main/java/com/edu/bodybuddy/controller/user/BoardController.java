package com.edu.bodybuddy.controller.user;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.board.FreeBoard;
import com.edu.bodybuddy.model.board.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("freeBoardService")
	private BoardService freeBoardService;
	
	@GetMapping("/free_detail/{free_board_idx}")
	public ModelAndView getDetail(@PathVariable int free_board_idx) {
		return null;
	}
	@GetMapping("/free_list/{index}")
	public ModelAndView getList(@PathVariable int index) {
		
		ModelAndView mav = new ModelAndView("/board/free_list");
		return mav;
	}
	
	@PostMapping("/free_regist")
	public ModelAndView regist(FreeBoard freeBoard) {
		logger.info("freeBoard : " + freeBoard);
		
		ModelAndView mav = new ModelAndView("/board/free_list");
		return mav;
	}
	
	
}
