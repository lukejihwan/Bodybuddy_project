package com.edu.bodybuddy.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;
import com.edu.bodybuddy.exception.ExrDetailRecordException;
import com.edu.bodybuddy.exception.ExrRecordException;
import com.edu.bodybuddy.model.myrecord.ExrRecordService;


@Controller
@RequestMapping("/myrecord")
public class MyRecordController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExrRecordService exrRecordService;
	
	@GetMapping("/addrecord")
	public ModelAndView getAddPage(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("myrecord/add_record");
		return mav;
	}
	
	@GetMapping("/physical_record")
	public ModelAndView getphysicalPage(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("myrecord/physical_record");
		return mav;
	}

	@GetMapping("/exr_record")
	public ModelAndView getExrPage(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("myrecord/exr_record");
		return mav;
	}
	
	@GetMapping("/diet_record")
	public ModelAndView getDietPage(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("myrecord/diet_record");
		return mav;
	}
	
	//운동기록 등록하는 곳
	@PostMapping("/exr_regist")
	public ModelAndView registRecord(HttpServletRequest request) {
		//일시키기
		String[] OneExr=request.getParameterValues("OneExr");
		//exrRecordService.regist(exrRecordList);
		
		//받아온 값 저장하기(등록하고 받을게 있나..? 고민해보자)
		
		ModelAndView mav=new ModelAndView("myrecord/add_record");
		return mav;
	}
	
	//임시용
	@ExceptionHandler({ExrRecordException.class, ExrDetailRecordException.class})
	public void handle(Exception e) {
		logger.info("ExrRecordException 오류발생 내용 :"+e.getMessage());
		logger.info("ExrRecordException 오류발생 세부 내용 :"+e);
	}

	
}



