package com.edu.bodybuddy.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.board.FreeBoard;
import com.edu.bodybuddy.domain.board.FreeBoardComment;
import com.edu.bodybuddy.model.board.BoardCommentService;
import com.edu.bodybuddy.util.Message;

import lombok.Builder.Default;

@RestController
@RequestMapping("/rest/board")
public class RestBoardCommentController {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	@Qualifier("freeBoardCommentService")
	private BoardCommentService freeBoardCommentService;
	
	
	@GetMapping("/free_board/comment/board/{free_board_idx}")
	/**
	 * 
	 * @param request AOP를 위한 매개변수
	 * @param free_board_idx 가져올 comments들이 포함된 게시글 idx
	 * @return free_board의 특정 게시글의 댓글들을 리스트로 반환
	 */
	public List<FreeBoardComment> getList(HttpServletRequest request, @PathVariable int free_board_idx){		
		
		//3단계
		//logger.info("코멘트 : "+free_board_idx);
		
		List boardCommentList = freeBoardCommentService.selectAllByBoard(free_board_idx);
		//logger.info("가져온 해당 게시글의 댓글"+boardCommentList);
		
		return boardCommentList;
	}
	
	@PostMapping("/free_board/comment")
	public ResponseEntity<Message> regist(HttpServletRequest request, FreeBoardComment freeBoardComment, int idx){
		
		FreeBoard freeBoard = new FreeBoard();
		freeBoard.setFree_board_idx(idx);
		freeBoardComment.setFreeBoard(freeBoard);
		freeBoardComment.setWriter("임시 작성자");
		//3단계
//		logger.info("regist freeBoardComment : " + freeBoardComment);
//		logger.info("regist board_idx : " + freeBoardComment.getFreeBoard().getFree_board_idx());
//		logger.info("regist board_comment_idx : " + freeBoardComment.getFree_board_comment_idx());
		freeBoardCommentService.insert(freeBoardComment);
		
		Message message = new Message();
		message.setCode(201);
		message.setMsg("댓글 등록 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	
}
