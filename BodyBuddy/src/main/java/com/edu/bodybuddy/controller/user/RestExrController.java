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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.domain.exr.ExrRoutine;
import com.edu.bodybuddy.domain.exr.ExrRoutineComment;
import com.edu.bodybuddy.domain.exr.ExrTip;
import com.edu.bodybuddy.exception.ExrCategoryException;
import com.edu.bodybuddy.exception.ExrRoutineException;
import com.edu.bodybuddy.model.exr.ExrCategoryService;
import com.edu.bodybuddy.model.exr.ExrRoutineCommentService;
import com.edu.bodybuddy.model.exr.ExrRoutineService;
import com.edu.bodybuddy.model.exr.ExrTipService;
import com.edu.bodybuddy.util.Msg;

@RestController
@RequestMapping("/rest/exr")
public class RestExrController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrCategoryService exrCategoryService;
	@Autowired
	private ExrRoutineService exrRoutineService;
	@Autowired
	private ExrRoutineCommentService exrRoutineCommentService;
	@Autowired
	private ExrTipService exrTipService; 
	
	/*---------------------
	 *  루틴 공유 게시판
	 * --------------------*/
	// 리스트 조회
	@GetMapping("/routine_list")
	public List<ExrRoutine> getRoutineList(int pg,HttpServletRequest request){
		
		//List<ExrRoutine> exrRoutineList=exrRoutineService.selectAll();
		List<ExrRoutine> exrRoutineList=exrRoutineService.selectAllByPage(pg);
		
		logger.info("문제있니? 확인 "+exrRoutineList);

		return exrRoutineList;
	}
	
	
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
	
	
	// 공유 게시판 글 수정
	@RequestMapping(value = "/routine", method = RequestMethod.PUT)
	public ResponseEntity<Msg> update(@RequestBody ExrRoutine exrRoutine) throws ExrCategoryException{
		logger.info("비동기 카테고리는????? "+exrRoutine.getExrCategory());
		exrRoutineService.update(exrRoutine);
		
		Msg msg=new Msg();
		msg.setMsg("루틴공유글 수정 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}

	
	// 검색 기능
	@GetMapping("/routine/search")
	public List<ExrRoutine> getSearch(String keyword, HttpServletRequest request) {
		logger.info("응답 받음");

		HashMap<String, String> map=new HashMap<String, String>();
		map.put("keyword", keyword);
		logger.info("맵의 문자열은? "+keyword);
		
		List<ExrRoutine> exrRoutineList=exrRoutineService.selectBySearch(map);
		
		return exrRoutineList;
	}
	
	
	// 태그(카테고리)별 조회
	@GetMapping("/routine_category/{exr_category_idx}")
	public List<ExrRoutine> getRoutineByCategory(@PathVariable int exr_category_idx, HttpServletRequest request){
		List<ExrRoutine> exrRoutineList=exrRoutineService.selectByFk(exr_category_idx);
		logger.info(""+exrRoutineList);
		return exrRoutineList;
	}
	
	
	// 추천 수 증가
	@GetMapping("/routine/recommend/{exr_routine_idx}")
	public ResponseEntity<Msg> plusReccomend(@PathVariable int exr_routine_idx, HttpServletRequest request){
		exrRoutineService.plusRecommend(exr_routine_idx);
		
		Msg msg=new Msg();
		msg.setMsg("추천 되었습니다");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	/*---------------------
	 *  댓글 관련 영역
	 * --------------------*/
	@PostMapping("/routine/comment")
	public ResponseEntity<Msg> regist(ExrRoutineComment exrRoutineComment, HttpServletRequest request){
		//logger.info("넘어온 글 확인 "+exrRoutineComment);
		
		// 댓글의 스텝과 뎁스를 1로 세팅 후 insert

		
		logger.info("들어가기 전에 세팅 잘 되었나 확인 "+exrRoutineComment.getStep());
		logger.info("들어가기 전에 세팅 잘 되었나 확인 "+exrRoutineComment.getDepth());
		exrRoutineCommentService.insert(exrRoutineComment);
		
		Msg msg=new Msg();
		msg.setMsg("댓글 등록 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	// 댓글 조회
	@GetMapping("/routine/comment/{exr_routine_idx}")
	public List<ExrRoutineComment> getCommentList(@PathVariable int exr_routine_idx, HttpServletRequest request){
		List<ExrRoutineComment> exrRoutineCommentList=exrRoutineCommentService.selectByFk(exr_routine_idx);
		//logger.info("댓글 리스트 확인 "+exrRoutineCommentList);
		
		return exrRoutineCommentList;
	}
	
	
	// 답글 등록
	// 자기 자신에 대한 아이디 엑스?
	@PostMapping("/routine/comment/reply")
	public ResponseEntity<Msg> reply(ExrRoutineComment exrRoutineComment, HttpServletRequest request){
		logger.info("넘어온 답글 reply 함수 확인 "+exrRoutineComment);
		
		
		exrRoutineCommentService.registReply(exrRoutineComment);
		
		Msg msg=new Msg();
		msg.setMsg("댓글 등록 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	// 댓글 삭제
	@DeleteMapping("/routine/comment/{exr_routine_comment_idx}")
	public ResponseEntity<Msg> delCommet(@PathVariable int exr_routine_comment_idx, HttpServletRequest request){
		logger.info("넘어오는지 확인! "+exr_routine_comment_idx);
		exrRoutineCommentService.delete(exr_routine_comment_idx);
		
		Msg msg=new Msg();
		msg.setMsg("댓글 삭제 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	
	/*------------------------------------
	  		운동 팁 게시판
	 --------------------------------------*/
	
	// 등록
	@PostMapping("/tip")
	public ResponseEntity<Msg> tipInsert(ExrTip exrTip) throws ExrCategoryException{
		logger.info("등록될 입력 값!"+exrTip);
		
		exrTipService.insert(exrTip);
		
		Msg msg=new Msg();
		msg.setMsg("팁글 추가 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	// 리스트 조회
	@GetMapping("/tip_list")
	public List<ExrRoutine> getTipList(HttpServletRequest request){
		List<ExrRoutine> exrTipList=exrTipService.selectAll();
		logger.info("문제있니? 확인 "+exrTipList);
		return exrTipList;
	}
	
	
	
	/*------------------------------------------
	  예외 객체
	 --------------------------------------------*/ 
	@ExceptionHandler(ExrRoutineException.class)
	public ResponseEntity<Msg> handle(ExrRoutineException e){
		Msg msg=new Msg();
		msg.setMsg("컨트롤러에서 오류 잡힘 e : "+e.getMessage());
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
}
