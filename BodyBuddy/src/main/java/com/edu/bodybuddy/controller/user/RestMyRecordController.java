package com.edu.bodybuddy.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;
import com.edu.bodybuddy.model.myrecord.ExrRecordService;
import com.edu.bodybuddy.util.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/rest/myrecord")
public class RestMyRecordController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExrRecordService exrRecordService;
	
	@GetMapping("/geo")
	public List getLocation(){
		
		return null;
	}
	
	@PostMapping("/exrList") //Rest방식의 이름을 어떻게 하는게 Restful적일까
	public ResponseEntity<Message> getExrRecords(@RequestBody List<ExrRecord> exrList) { //Post방식으로 보내야 Body가 있기 때문에 RequestBody로 받을 수 있는 것
		//List<Objcet> 로 받아오니까 받아와짐 : Json객체 하나를 Object로 받아오면 되기 때문
		
		/*
		ObjectMapper objectMapper=new ObjectMapper();
		for(int i=0; i<exrList.size(); i++) {
			ExrRecord exrRecord=exrList.get(i);
			logger.info("받아온 운동명은 : "+exrRecord.getExr_name());
		}
		*/
		
		logger.info("운동목록 받아오는 : "+exrList);
		exrRecordService.regist(exrList);
		
		return null;
	}

}
