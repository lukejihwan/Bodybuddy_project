package com.edu.bodybuddy.controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.domain.exr.ExrRoutine;
import com.edu.bodybuddy.domain.exr.ExrTip;
import com.edu.bodybuddy.domain.exr.ExrToday;
import com.edu.bodybuddy.exception.ExrCategoryException;
import com.edu.bodybuddy.exception.ExrRoutineCommentException;
import com.edu.bodybuddy.exception.ExrRoutineException;
import com.edu.bodybuddy.exception.ExrTipException;
import com.edu.bodybuddy.exception.ExrTodayException;
import com.edu.bodybuddy.model.exr.ExrCategoryService;
import com.edu.bodybuddy.model.exr.ExrNoticeService;
import com.edu.bodybuddy.model.exr.ExrRoutineService;
import com.edu.bodybuddy.model.exr.ExrTipService;
import com.edu.bodybuddy.model.exr.ExrTodayService;
import com.edu.bodybuddy.util.Msg;
import com.edu.bodybuddy.util.PageManager;

// 운동 카테고리 제어 컨트롤러
@Controller
@RequestMapping("/exr")
public class ExcerciseController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrNoticeService exrNoticeService;
	@Autowired
	private ExrCategoryService exrCategoryService;
	@Autowired
	private ExrRoutineService exrRoutineService;
	@Autowired
	private ExrTipService exrTipService;
	@Autowired
	private ExrTodayService exrTodayService;
	
	/*-------------------
	 *  운동 정보 게시판
	 * ------------------*/
	// 메인페이지
	@GetMapping("/notice")
	public ModelAndView getMain(HttpServletRequest request) {
		List<ExrNotice> exrNoticeList=exrNoticeService.selectAll();
		ModelAndView mv= new ModelAndView("exr/notice_list");
		mv.addObject("exrNoticeList", exrNoticeList);
		return mv;
	}

	
	// 상세페이지
	@GetMapping("/notice/{exr_category_idx}")
	public ModelAndView getdetail(@PathVariable("exr_category_idx") int exr_category_idx, HttpServletRequest request) {
		
		ExrNotice exrNotice=exrNoticeService.selectByCategory(exr_category_idx);
		List<ExrNotice> exrCategoryList=exrCategoryService.selectAll();
		
		logger.info("유저 페이지 작동 : "+exrNotice);
		
		ModelAndView mv= new ModelAndView("exr/notice_detail");
		mv.addObject("exrNotice", exrNotice);
		mv.addObject("exrCategoryList", exrCategoryList);
		return mv;
	}
	
	
	
	/*-------------------
	 *  루틴 공유 게시판
	 * ------------------*/
	// 리스트 조회
	@GetMapping("/routine_list/{pg}")
	public ModelAndView getList(@PathVariable int pg, HttpServletRequest request){
		int total=exrRoutineService.totalCount();
		logger.info("토탈 레코드 수는?"+total);
		logger.info("현제 페이지는? "+pg);
		
		List<ExrNotice> exrCategoryList=exrCategoryService.selectAll();
		
		PageManager pageManager=new PageManager();
		pageManager.init(total, pg);
		
		ModelAndView mv= new ModelAndView("exr/routine_list");
		mv.addObject("pageManager", pageManager);
		mv.addObject("exrCategoryList", exrCategoryList);
		return mv;
	}
	
	
	// 글 등록 폼
	@GetMapping("/routine/registform")
	public ModelAndView getTipForm(HttpServletRequest request){
		List<ExrNotice> exrCategoryList=exrCategoryService.selectAll();
		
		ModelAndView mv= new ModelAndView("exr/routine_registform");
		mv.addObject("exrCategoryList", exrCategoryList);
		return mv;
	}
	
	
	// 상세 보기
	@GetMapping("/routine_detail/{exr_routine_idx}")
	public ModelAndView getRoutineDetail(@PathVariable int exr_routine_idx, HttpServletRequest request){
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		ExrRoutine exrRoutine=exrRoutineService.select(exr_routine_idx);
		
		// 조회수 증가
		exrRoutineService.plusHit(exr_routine_idx);
		
		ModelAndView mv= new ModelAndView("exr/routine_detail");
		mv.addObject("exrCategoryList", exrCategoryList);
		mv.addObject("exrRoutine", exrRoutine);
		return mv;
	}
	
	
	// 수정폼
	@GetMapping("/routine/edit/{exr_routine_idx}")
	public ModelAndView getEditForm(@PathVariable int exr_routine_idx, HttpServletRequest request){
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		ExrRoutine exrRoutine=exrRoutineService.select(exr_routine_idx);
		
		ModelAndView mv= new ModelAndView("exr/routine_edit");
		mv.addObject("exrCategoryList", exrCategoryList);
		mv.addObject("exrRoutine", exrRoutine);
		return mv;
	}
	
	
	// 삭제
	@GetMapping("/routine/delete")
	public ModelAndView delete(int exr_routine_idx, HttpServletRequest request) {
		exrRoutineService.delete(exr_routine_idx);
		
		ModelAndView mv= new ModelAndView("redirect:/exr/routine_list/1");
		return mv;
	}
	

	
	/*-----------------------
	  	팁 게시판
	 -------------------------*/ 
	@GetMapping("/tip_list")  
	public ModelAndView getRegistForm(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("/exr/tip_main");
		return mv;
	}
	
	
	// 등록폼
	@GetMapping("/tip/registfrom")
	public ModelAndView getTipMain(HttpServletRequest request) {
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		
		ModelAndView mv= new ModelAndView("/exr/tip_registform");
		mv.addObject("exrCategoryList", exrCategoryList);
		return mv;
	}
	
	
	// 상세 보기
	@GetMapping("/tip_detail/{exr_tip_idx}")
	public ModelAndView getTipDetail(@PathVariable int exr_tip_idx, HttpServletRequest request){
		ExrTip exrTip=exrTipService.select(exr_tip_idx);
		
		// 조회수 증가
		exrTipService.plusHit(exr_tip_idx);
		
		ModelAndView mv= new ModelAndView("exr/tip_detail");
		mv.addObject("exrTip", exrTip);
		return mv;
	}


	// 수정폼
	@GetMapping("/tip/edit/{exr_tip_idx}")
	public ModelAndView getTipEditForm(@PathVariable int exr_tip_idx, HttpServletRequest request){
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		ExrTip exrTip=exrTipService.select(exr_tip_idx);
		
		ModelAndView mv= new ModelAndView("exr/tip_edit");
		mv.addObject("exrCategoryList", exrCategoryList);
		mv.addObject("exrTip", exrTip);
		return mv;
	}
	
	
	// 삭제
	@GetMapping("/tip/delete")
	public ModelAndView deleteTip(int exr_tip_idx, HttpServletRequest request) {
		exrTipService.delete(exr_tip_idx);
		ModelAndView mv= new ModelAndView("redirect:/exr/tip_list");
		return mv;
	}
	
	
	
	/*-----------------------
  		오운완 게시판
 	-------------------------*/ 
	// 리스트
	@GetMapping("/today_list")
	public ModelAndView getTipList(HttpServletRequest request){
		List<ExrToday> exrTodayList=exrTodayService.selectAll();
		logger.info("컨트롤러에서 확인 "+exrTodayList);
		
		ModelAndView mv= new ModelAndView("exr/today_list");
		mv.addObject("exrTodayList", exrTodayList);
		return mv;
	}
	
	
	// 등록폼
	@GetMapping("/today/registform")
	public ModelAndView getTipRegistForm(HttpServletRequest request){
		return new ModelAndView("exr/today_registform");
	}
	
	
	// 상세폼
	@GetMapping("/today_detail")
	public ModelAndView getTodayDetail(int exr_today_idx, HttpServletRequest request){
		ExrToday exrToday=exrTodayService.select(exr_today_idx);
		
		// 조회수 증가
		exrTodayService.plusHit(exr_today_idx);
		
		ModelAndView mv= new ModelAndView("exr/today_detail");
		mv.addObject("exrToday", exrToday);
		return mv;
	}
	
	
	// 수정폼
	@GetMapping("/today/editform")
	public ModelAndView getTodayEditForm( int exr_today_idx, HttpServletRequest request){
		ExrToday exrToday=exrTodayService.select(exr_today_idx);
		
		ModelAndView mv= new ModelAndView("exr/today_edit");
		mv.addObject("exrToday", exrToday);
		return mv;
	}
	
	
	// 삭제
	@GetMapping("/today/delete")
	public ModelAndView deleteToday(int exr_today_idx, HttpServletRequest request) {
		exrTodayService.delete(exr_today_idx);
		ModelAndView mv= new ModelAndView("redirect:/exr/today_list");
		return mv;
	}
	
	
	
	/*------------------------------------------
	  예외 객체
	 --------------------------------------------*/ 
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
	
}
