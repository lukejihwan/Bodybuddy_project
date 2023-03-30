package com.edu.bodybuddy.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.myrecord.DailyWalk;
import com.edu.bodybuddy.model.myrecord.DailyWalkService;
import com.edu.bodybuddy.util.Message;

@RestController
@RequestMapping("/rest/ranking")
public class RestRankingController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DailyWalkService dailyWalkService;
	
	@GetMapping("/walk/daily")
	public List getDailyRanking(){
		
		List<HashMap<String, Object>> list = new ArrayList();
		
		for(int i =9;i>=0;i--) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Member member = new Member();
			member.setMember_idx((i+1)*2);
			member.setNickname("유저 " + i);
			
			
			map.put("dailywalk_idx", i+1);
			map.put("member", member);
			map.put("distance", 500 * (i+1));
			map.put("regdate", "2023-03-29 11:52:31");
			list.add(map);
		}
		
		return list;
	}
	
	@PostMapping("/walk")
	public ResponseEntity<Message> regist(HttpServletRequest request, @RequestBody DailyWalk dailyWalk){
		
		logger.info("서버가 전달받은 객체 "+dailyWalk);
		
		//3단계
		dailyWalkService.regist(dailyWalk);
		
		Message message = new Message("일일 기록 등록 성공", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
	
	
	@DeleteMapping("/walk")
	public ResponseEntity<Message> delete(HttpServletRequest request, @RequestBody DailyWalk dailyWalk){
		
		//3단계
		dailyWalkService.delete(dailyWalk.getDailywalk_idx());
		
		Message message = new Message("일일 기록 삭제 성공", 200);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;
	}
}
