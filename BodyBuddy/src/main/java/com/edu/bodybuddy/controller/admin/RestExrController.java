package com.edu.bodybuddy.controller.admin;

import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.board.FreeBoard;
import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.exception.ExrCategoryException;
import com.edu.bodybuddy.exception.ExrNoticeException;
import com.edu.bodybuddy.model.exr.ExrCategoryService;
import com.edu.bodybuddy.model.exr.ExrNoticeImgService;
import com.edu.bodybuddy.model.exr.ExrNoticeService;
import com.edu.bodybuddy.util.Message;
import com.edu.bodybuddy.util.Msg;

// 운동 정보 페이지 Admin ver.
// 웹에서 접근시 /admin 붙이기
@RestController
@RequestMapping("/rest/exr")
public class RestExrController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrCategoryService exrCategoryService;
	@Autowired
	private ExrNoticeService exrNoticeService;
	@Autowired
	private ExrNoticeImgService exrNoticeImgService;
	
	/*-----------------------------
		운동 카테고리와 관련된 메서드!
	------------------------------*/ 
	@GetMapping("/category_list")
	public List getCategoryList(HttpServletRequest request) {
		List<ExrCategory> exrCategoryList=exrCategoryService.selectAll();
		return exrCategoryList;
	}
	
	
	// 카테고리 등록
	@PostMapping("/category")
	public ResponseEntity<Msg> insert(ExrCategory exrCategory) throws ExrCategoryException{
		exrCategoryService.insert(exrCategory);
		
		Msg msg=new Msg();
		msg.setMsg("카테고리 추가 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	// 카테고리 수정
	@PutMapping("/category")
	public ResponseEntity<Msg> edit(@RequestBody ExrCategory exrCategory) throws ExrCategoryException{
		logger.info("수정할 카테고리 잘 넘어오나요~~ "+exrCategory);
		exrCategoryService.update(exrCategory);
		
		Msg msg=new Msg();
		msg.setMsg("카테고리 수정 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	// 카테고리 삭제
	@DeleteMapping("/category/{exr_category_idx}")
	public ResponseEntity<String> del(@PathVariable("exr_category_idx") int exr_category_idx) throws ExrCategoryException{
		exrCategoryService.delete(exr_category_idx);
		
		ResponseEntity<String> entity=new ResponseEntity<String>("카테고리 삭제되었음", HttpStatus.OK);
		return entity;
	}
	
	
	
	/*-------------------------------------
	  운동 정보 (ExrNotice) 와 관련된 메서드들
	 --------------------------------------*/ 
	
	// 정보글 등록
	@PostMapping("/notice")
	public ResponseEntity<String> regsit(ExrNotice exrNotice, HttpServletRequest request){
		MultipartFile[] photoList=exrNotice.getPhoto();
		logger.info("사진 배열"+photoList.length);
		
		
		// 사진 등록하기
		ServletContext context=request.getSession().getServletContext();
		String dir=context.getRealPath("/resources/data/exr/");		// 실제 디렉토리 폴더 만드는 것 잊지 말기!
		logger.info("가진 저장 경로 : "+dir);
		
		logger.info("컨트롤러에서 노티스의 카테고리 확인 "+exrNotice);
		exrNoticeService.regist(exrNotice, dir);
		
		ResponseEntity<String> entity=new ResponseEntity<String>("상품 등록 완료", HttpStatus.OK);
		return entity;
	}
	
	
	
	// 정보글 수정
	@PostMapping("/notice/edit")
	public ResponseEntity<String> edit(ExrNotice exrNotice, HttpServletRequest request){
		MultipartFile[] photoList=exrNotice.getPhoto();
		logger.info("사진 배열"+photoList.length);
		
		
		// 사진 등록하기
		ServletContext context=request.getSession().getServletContext();
		String dir=context.getRealPath("/resources/data/exr/");		// 실제 디렉토리 폴더 만드는 것 잊지 말기!
		logger.info("가진 저장 경로 : "+dir);
		
		//exrNoticeService.regist(exrNotice, dir);
		
		ResponseEntity<String> entity=new ResponseEntity<String>("수정 완료", HttpStatus.OK);
		return entity;
	}
	
	

	
	
	
	/*------------------------------------------
	  운동 정보 이미지(ExrNoticeImg) 와 관련된 메서드
	 --------------------------------------------*/ 
	
	// 이미지 삭제
	@DeleteMapping("/notice_img/{exr_notice_idx}")
	public ResponseEntity<Message> delImg(@PathVariable("exr_notice_idx") int exr_notice_idx) throws ExrCategoryException{
		logger.info("넘어오드나 "+exr_notice_idx);
		exrNoticeImgService.delete(exr_notice_idx);
		
		Message message = new Message("사진 삭제 완료");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
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
	
	
}
