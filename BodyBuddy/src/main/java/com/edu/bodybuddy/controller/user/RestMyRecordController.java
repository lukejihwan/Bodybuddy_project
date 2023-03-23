package com.edu.bodybuddy.controller.user;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;
import com.edu.bodybuddy.exception.ExrDetailRecordException;
import com.edu.bodybuddy.exception.ExrRecordException;
import com.edu.bodybuddy.model.myrecord.ExrRecordService;
import com.edu.bodybuddy.model.myrecord.MyRecordService;
import com.edu.bodybuddy.util.Message;

@RestController
@RequestMapping("/rest/myrecord")
public class RestMyRecordController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MyRecordService myRecordService;
	
	@Autowired
	private ExrRecordService exrRecordService;
	
	
	@PostMapping("/location")
	public String getLocation(){
		
		logger.info("응답 받음");
		
		return "지금 확인 중";
	}
	
	@GetMapping("/weatherAPI/{nx}/{ny}")
	public Map<String, String> getWeather(@PathVariable(name="nx") int nx, @PathVariable(name="ny") int ny) {
		
		logger.info("받아온 nx값은 : "+nx);
		logger.info("받아온 ny값은 : "+ny);
		
		Map<String, String> dataForResponseMap=myRecordService.getWeather(nx, ny);
		return dataForResponseMap;
	}
	
	//한달간의 기록을 보여주는 메서드
	//ResponseBody 를 붙일 필요없음, RestController로 클래스 선언이 되어있기 때문에
	@PostMapping("/exrListForMonth")
	public List<ExrRecord> getExrRecordForMonth(@RequestBody Map<String, String> oneMonthPeriod){
		logger.info("한달동안의 기록을 불러올 첫날과 마지막 날 값은 :" +oneMonthPeriod.get("firstDay")+",,"+oneMonthPeriod.get("lastDay"));
		List<ExrRecord> exrRecordListMonth=exrRecordService.seletForMonth(oneMonthPeriod);
		return exrRecordListMonth;
	}
	
	@PostMapping("/exrList") //Rest방식의 이름을 어떻게 하는게 Restful적일까
	public ResponseEntity<Message> getExrRecords(@RequestBody List<ExrRecord> exrList) { //Post방식으로 보내야 Body가 있기 때문에 RequestBody로 받을 수 있는 것
		//List<Objcet> 로 받아오니까 받아와짐 : Json객체 하나를 Object로 받아오면 되기 때문
		
		/*테스트 영역
		ObjectMapper objectMapper=new ObjectMapper();
		for(int i=0; i<exrList.size(); i++) {
			ExrRecord exrRecord=exrList.get(i);
			logger.info("받아온 운동명은 : "+exrRecord.getExr_name());
		}
		*/
		logger.info("운동목록 받아오는 : "+exrList);
		exrRecordService.regist(exrList);
		Message msg=new Message();
		msg.setMsg("성공적으로 등록되었습니다");
		ResponseEntity<Message> entity=new ResponseEntity<Message>(msg, HttpStatus.OK);
		return entity;
	}
	
	//해당일의 운동 기록리스트를 가져오는 메서드
	@GetMapping("/exrRecord/{regdate}")
	public List<ExrRecord> getExrRecord(@PathVariable("regdate") String regdate){
		logger.info("받아온 값은"+ regdate);
		List<ExrRecord> exrList=exrRecordService.selectForDay(regdate);
		return exrList;
	}
	
	
	//이 예외처리를 나중에 controllerAdvice로 처리해줄지 아니면 이렇게 처리해줄지는 나중에 보자
	//예외를 처리하는 전역적 ExceptionHandler
	@ExceptionHandler({ExrRecordException.class, ExrDetailRecordException.class})
	public ResponseEntity<Message> handle(RuntimeException e){
		
		Message message=new Message();
		message.setMsg(e.getMessage());
		
		//임시로 BAD_REQUEST로 설정해 놓음
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		
		return entity;
	}

}





