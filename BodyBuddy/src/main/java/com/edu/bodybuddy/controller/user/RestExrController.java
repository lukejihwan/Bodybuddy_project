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
import com.edu.bodybuddy.domain.exr.ExrToday;
import com.edu.bodybuddy.domain.exr.ExrTodayComment;
import com.edu.bodybuddy.domain.myrecord.GpsData;
import com.edu.bodybuddy.exception.ExrCategoryException;
import com.edu.bodybuddy.exception.ExrRoutineCommentException;
import com.edu.bodybuddy.exception.ExrRoutineException;
import com.edu.bodybuddy.exception.ExrTipException;
import com.edu.bodybuddy.exception.ExrTodayCommentException;
import com.edu.bodybuddy.exception.ExrTodayException;
import com.edu.bodybuddy.model.exr.ExrCategoryService;
import com.edu.bodybuddy.model.exr.ExrRoutineCommentService;
import com.edu.bodybuddy.model.exr.ExrRoutineService;
import com.edu.bodybuddy.model.exr.ExrTipService;
import com.edu.bodybuddy.model.exr.ExrTodayCommentService;
import com.edu.bodybuddy.model.exr.ExrTodayService;
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
	@Autowired
	private ExrTodayService exrTodayService; 
	@Autowired
	private ExrTodayCommentService exrTodayCommentService; 
	
	/*---------------------
	 *  루틴 공유 게시판
	 * --------------------*/
	// 리스트 조회
	@GetMapping("/routine_list")
	public List<ExrRoutine> getRoutineList(int pg,HttpServletRequest request){
		return exrRoutineService.selectAll();
	}
	
	
	// 등록
	@PostMapping("/routine")
	public ResponseEntity<Msg> insert(ExrRoutine exrRoutine) throws ExrCategoryException{
		logger.info("넘어온 루틴 글 컨트롤러에서 확인 :"+exrRoutine);
		logger.info("멤버 아이디가 뭔데 :"+exrRoutine.getMember().getMember_idx());
		System.out.println("넘어온 루틴 글 컨트롤러에서 확인 :"+exrRoutine);
		System.out.println("멤버 아이디가 뭔데 :"+exrRoutine.getMember().getMember_idx());
		exrRoutineService.insert(exrRoutine);
		
		Msg msg=new Msg();
		msg.setMsg("루틴공유글 추가 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	// 공유 게시판 글 수정
	@RequestMapping(value = "/routine", method = RequestMethod.PUT)
	public ResponseEntity<Msg> update(@RequestBody ExrRoutine exrRoutine) throws ExrCategoryException{
		exrRoutineService.update(exrRoutine);
		
		Msg msg=new Msg();
		msg.setMsg("루틴공유글 수정 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}

	
	// 검색 기능
	@GetMapping("/routine/search")
	public List<ExrRoutine> getSearch(String keyword, HttpServletRequest request) {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("keyword", keyword);
		
		List<ExrRoutine> exrRoutineList=exrRoutineService.selectBySearch(map);
		return exrRoutineList;
	}
	
	
	// 태그(카테고리)별 조회
	@GetMapping("/routine_category/{exr_category_idx}")
	public List<ExrRoutine> getRoutineByCategory(@PathVariable int exr_category_idx, HttpServletRequest request){
		return exrRoutineService.selectByFk(exr_category_idx);
	}
	
	
	// 추천 수 증가  --> 후 그 값 반환함
	@GetMapping("/routine/recommend/{exr_routine_idx}")
	public ResponseEntity<Integer> plusReccomend(@PathVariable int exr_routine_idx, HttpServletRequest request){
		exrRoutineService.plusRecommend(exr_routine_idx);
		
		ExrRoutine exrRoutine=exrRoutineService.select(exr_routine_idx);
		int recommend=exrRoutine.getRecommend();
		
		ResponseEntity<Integer> entity=new ResponseEntity<Integer>(recommend, HttpStatus.OK);
		return entity;
	}
	
	
	/*---------------------
	 *  댓글 관련 영역
	 * --------------------*/
	// 댓글 등록
	@PostMapping("/routine/comment")
	public ResponseEntity<Msg> registRoutineComment(ExrRoutineComment exrRoutineComment, HttpServletRequest request){
		exrRoutineCommentService.insert(exrRoutineComment);
		
		Msg msg=new Msg();
		msg.setMsg("댓글 등록 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}

	
	
	// 댓글 조회
	@GetMapping("/routine/comment/{exr_routine_idx}")
	public List<ExrRoutineComment> getRoutineCommentList(@PathVariable int exr_routine_idx, HttpServletRequest request){
		return exrRoutineCommentService.selectByFk(exr_routine_idx);
	}
	
	
	// 대댓글 등록
	// 자기 자신에 대한 아이디 엑스?
	@PostMapping("/routine/comment/reply")
	public ResponseEntity<Msg> replyRoutineComment(ExrRoutineComment exrRoutineComment, HttpServletRequest request){
		logger.info("넘어온 답글 reply 함수 확인 "+exrRoutineComment);
		exrRoutineCommentService.registReply(exrRoutineComment);
		
		Msg msg=new Msg();
		msg.setMsg("댓글 등록 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	// 댓글 삭제
	@DeleteMapping("/routine/comment/{exr_routine_comment_idx}")
	public ResponseEntity<Msg> delRoutineCommet(@PathVariable int exr_routine_comment_idx, HttpServletRequest request){
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
	
	
	// 추천 수 증가  --> 후 그 값 반환함
	@GetMapping("/tip/recommend/{exr_tip_idx}")
	public ResponseEntity<Integer> plusTipReccomend(@PathVariable int exr_tip_idx, HttpServletRequest request){
		exrTipService.plusRecommend(exr_tip_idx);
		
		ExrTip exrTip=exrTipService.select(exr_tip_idx);
		int recommend=exrTip.getRecommend();
		
		ResponseEntity<Integer> entity=new ResponseEntity<Integer>(recommend, HttpStatus.OK);
		return entity;
	}
	
	
	/*------------------------------------
		오운완 게시판
	--------------------------------------*/
	// 등록  ** 안드로이드 작업  **
	@PostMapping("/today")
	public ResponseEntity<Msg> todayInsert(ExrToday exrToday) throws ExrCategoryException{
		System.out.println("컨트롤러에서 확인 "+exrToday);
		logger.info("분명히 날라온 거 다 알고 있어 "+exrToday);

		
		// 안드로이드에서 받음!
		exrTodayService.insert(exrToday);
		
		Msg msg=new Msg();
		msg.setMsg("오운완 추가 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	
	// 추천 수 증가  --> 후 그 값 반환함
	@GetMapping("/today/recommend/{exr_today_idx}")
	public ResponseEntity<Integer> plusTodayReccomend(@PathVariable int exr_today_idx, HttpServletRequest request){
		exrTodayService.plusRecommend(exr_today_idx);
		
		ExrToday exrToday=exrTodayService.select(exr_today_idx);
		int recommend=exrToday.getRecommend();
		
		ResponseEntity<Integer> entity=new ResponseEntity<Integer>(recommend, HttpStatus.OK);
		return entity;
	}
	
	
	@PutMapping("/today")
	public ResponseEntity<Msg> editToday(@RequestBody ExrToday exrToday, HttpServletRequest request){
		exrTodayService.update(exrToday);
		
		Msg msg=new Msg();
		msg.setMsg("수정되었습니다");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	
	/*-------------------------
	 *  오운완 댓글 관련 영역
	 * ------------------------*/
	// 댓글 조회
	@GetMapping("/today/comment/{exr_today_idx}")
	public List<ExrTodayComment> getTodayCommentList(@PathVariable int exr_today_idx, HttpServletRequest request){
		return exrTodayCommentService.selectAllByToday(exr_today_idx);
	}
	
	
	// 댓글 등록
	@PostMapping("/today/comment")
	public ResponseEntity<Msg> registTodayComment(ExrTodayComment exrTodayComment, HttpServletRequest request){
		exrTodayCommentService.insert(exrTodayComment);
		
		Msg msg=new Msg();
		msg.setMsg("댓글 등록 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	// 대댓글 등록
	// 자기 자신에 대한 아이디 엑스?
	@PostMapping("/today/comment_reply")
	public ResponseEntity<Msg> replyTodayComment(ExrTodayComment exrTodayComment, HttpServletRequest request){
		logger.info("넘어온 답글 reply 함수 확인 "+exrTodayComment);
		
		logger.info("컨트롤러에서 넣기 전에 확인"+exrTodayComment);
		exrTodayCommentService.registReply(exrTodayComment);
		logger.info("컨트롤러에서 넣은 후 확인"+exrTodayComment);
		
		Msg msg=new Msg();
		msg.setMsg("답글 등록 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	// 댓글 삭제
	@DeleteMapping("/today/comment/{exr_today_comment_idx}")
	public ResponseEntity<Msg> delTodayCommet(@PathVariable int exr_today_comment_idx, HttpServletRequest request){
		logger.info("넘어오는지 확인! "+exr_today_comment_idx);
		exrTodayCommentService.delete(exr_today_comment_idx);
		
		Msg msg=new Msg();
		msg.setMsg("댓글 삭제 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	
	/*-----------------------------
	  예외 객체
	 ------------------------------*/ 
	@ExceptionHandler(ExrCategoryException.class)
	public ResponseEntity<Msg> handle(ExrCategoryException e){
		Msg msg=new Msg();
		msg.setMsg(e.getMessage());
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	@ExceptionHandler(ExrRoutineException.class)
	public ResponseEntity<Msg> handle(ExrRoutineException e){
		Msg msg=new Msg();
		msg.setMsg("컨트롤러에서 오류 잡힘 e : "+e.getMessage());
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	@ExceptionHandler(ExrRoutineCommentException.class)
	public ResponseEntity<Msg> handle(ExrRoutineCommentException e){
		Msg msg=new Msg();
		msg.setMsg("컨트롤러에서 오류 잡힘 e : "+e.getMessage());
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	@ExceptionHandler(ExrTipException.class)
	public ResponseEntity<Msg> handle(ExrTipException e){
		Msg msg=new Msg();
		msg.setMsg("컨트롤러에서 오류 잡힘 e : "+e.getMessage());
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	@ExceptionHandler(ExrTodayException.class)
	public ResponseEntity<Msg> handle(ExrTodayException e){
		Msg msg=new Msg();
		msg.setMsg("컨트롤러에서 오류 잡힘 e : "+e.getMessage());
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	@ExceptionHandler(ExrTodayCommentException.class)
	public ResponseEntity<Msg> handle(ExrTodayCommentException e){
		Msg msg=new Msg();
		msg.setMsg("컨트롤러에서 오류 잡힘 e : "+e.getMessage());
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
}
