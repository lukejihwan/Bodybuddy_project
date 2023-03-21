package com.edu.bodybuddy.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.edu.bodybuddy.domain.board.CounsellingBoardComment;
import com.edu.bodybuddy.domain.board.FreeBoard;
import com.edu.bodybuddy.domain.board.FreeBoardComment;
import com.edu.bodybuddy.domain.board.QnaBoardComment;
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
	
	@Autowired
	@Qualifier("qnaBoardCommentService")
	private BoardCommentService qnaBoardCommentService;
	
	@Autowired
	@Qualifier("counsellingBoardCommentService")
	private BoardCommentService counsellingBoardCommentService;
	
	
	// @자유게시판 코멘트 영역
	@GetMapping("/freeBoard/comment/board/{free_board_idx}")
	public List<FreeBoardComment> getFreeList(HttpServletRequest request, @PathVariable int free_board_idx){		
		
		//3단계
		//logger.info("코멘트 : "+free_board_idx);
		
		List boardCommentList = freeBoardCommentService.selectAllByBoard(free_board_idx);
		//logger.info("가져온 해당 게시글의 댓글"+boardCommentList);
		
		return boardCommentList;
	}
	
	@PostMapping("/freeBoard/comment")
	public ResponseEntity<Message> registFree(HttpServletRequest request, FreeBoardComment freeBoardComment){
		
		//3단계
		logger.info("regist freeBoardComment : " + freeBoardComment);
		freeBoardCommentService.insert(freeBoardComment);
		
		Message message = new Message();
		message.setCode(201);
		message.setMsg("댓글 등록 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@PutMapping("/freeBoard/comment")
	public ResponseEntity<Message> updateFree(HttpServletRequest request, @RequestBody FreeBoardComment freeBoardComment){
		
		//3단계
		//logger.info("update free_board_comment_idx : " + freeBoardComment.getFree_board_comment_idx());
		//logger.info("update comment : " + freeBoardComment.getComment());
		freeBoardCommentService.update(freeBoardComment);
		
		
		Message message = new Message();
		message.setCode(201);
		message.setMsg("댓글 수정 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@DeleteMapping("/freeBoard/comment/{free_board_comment_idx}")
	public ResponseEntity<Message> deleteFree(HttpServletRequest request, @PathVariable int free_board_comment_idx){
		
		//3단계
		//logger.info("comment delete free_board_comment_idx : "+free_board_comment_idx);
		freeBoardCommentService.delete(free_board_comment_idx);
		
		Message message = new Message();
		message.setCode(200);
		message.setMsg("댓글 삭제 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	// @QnA게시판 코멘트 영역
	@GetMapping("/qnaBoard/comment/board/{qna_board_idx}")
	public List<QnaBoardComment> getQnaList(HttpServletRequest request, @PathVariable int qna_board_idx){		
		
		//3단계
		//logger.info("코멘트 : "+qna_board_idx);
		
		List boardCommentList = qnaBoardCommentService.selectAllByBoard(qna_board_idx);
		//logger.info("가져온 해당 게시글의 댓글"+boardCommentList);
		
		return boardCommentList;
	}
	
	@PostMapping("/qnaBoard/comment")
	public ResponseEntity<Message> registQna(HttpServletRequest request, QnaBoardComment qnaBoardComment){
		
		//3단계
		logger.info("regist qnaBoardComment : " + qnaBoardComment);
		qnaBoardCommentService.insert(qnaBoardComment);
		
		Message message = new Message();
		message.setCode(201);
		message.setMsg("댓글 등록 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@PutMapping("/qnaBoard/comment")
	public ResponseEntity<Message> updateQna(HttpServletRequest request, @RequestBody QnaBoardComment qnaBoardComment){
		
		//3단계
		//logger.info("update qna_board_comment_idx : " + qnaBoardComment.getQna_board_comment_idx());
		//logger.info("update comment : " + qnaBoardComment.getComment());
		qnaBoardCommentService.update(qnaBoardComment);
		
		
		Message message = new Message();
		message.setCode(201);
		message.setMsg("댓글 수정 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@DeleteMapping("/qnaBoard/comment/{qna_board_comment_idx}")
	public ResponseEntity<Message> deleteQna(HttpServletRequest request, @PathVariable int qna_board_comment_idx){
		
		//3단계
		//logger.info("comment delete qna_board_comment_idx : "+qna_board_comment_idx);
		qnaBoardCommentService.delete(qna_board_comment_idx);
		
		Message message = new Message();
		message.setCode(200);
		message.setMsg("댓글 삭제 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	// @고민상담 게시판 영역
	@GetMapping("/counsellingBoard/comment/board/{counselling_board_idx}")
	public List<CounsellingBoardComment> getCounsellingList(HttpServletRequest request, @PathVariable int counselling_board_idx){		
		
		//3단계
		//logger.info("코멘트 : "+counselling_board_idx);
		
		List boardCommentList = counsellingBoardCommentService.selectAllByBoard(counselling_board_idx);
		//logger.info("가져온 해당 게시글의 댓글"+boardCommentList);
		
		return boardCommentList;
	}
	
	@PostMapping("/counsellingBoard/comment")
	public ResponseEntity<Message> registCounselling(HttpServletRequest request, CounsellingBoardComment counsellingBoardComment){
		
		//3단계
		logger.info("regist counsellingBoardComment : " + counsellingBoardComment);
		counsellingBoardCommentService.insert(counsellingBoardComment);
		
		Message message = new Message();
		message.setCode(201);
		message.setMsg("댓글 등록 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@PutMapping("/counsellingBoard/comment")
	public ResponseEntity<Message> updateCounselling(HttpServletRequest request, @RequestBody CounsellingBoardComment counsellingBoardComment){
		
		//3단계
		//logger.info("update counselling_board_comment_idx : " + counsellingBoardComment.getCounselling_board_comment_idx());
		//logger.info("update comment : " + counsellingBoardComment.getComment());
		counsellingBoardCommentService.update(counsellingBoardComment);
		
		
		Message message = new Message();
		message.setCode(201);
		message.setMsg("댓글 수정 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@DeleteMapping("/counsellingBoard/comment/{counselling_board_comment_idx}")
	public ResponseEntity<Message> deleteCounselling(HttpServletRequest request, @PathVariable int counselling_board_comment_idx){
		
		//3단계
		//logger.info("comment delete counselling_board_comment_idx : "+counselling_board_comment_idx);
		counsellingBoardCommentService.delete(counselling_board_comment_idx);
		
		Message message = new Message();
		message.setCode(200);
		message.setMsg("댓글 삭제 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
}
