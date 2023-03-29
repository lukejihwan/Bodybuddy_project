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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.myrecord.DailyWalk;
import com.edu.bodybuddy.model.main.BoardRankService;
import com.edu.bodybuddy.model.myrecord.DailyWalkService;
import com.edu.bodybuddy.util.Message;

@RestController
@RequestMapping("/rest/ranking")
public class RestRankingController {
	
	@Autowired
	private DailyWalkService dailyWalkService;
	
	@Autowired
	private BoardRankService boardRankService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/walk/daily")
	public List getDailyWalkRanking(){
		
		List rankList = dailyWalkService.selectAllDailyWalkForDay();
//		List<HashMap<String, Object>> rankList = dailyWalkService.selectAllDailyWalkForDay();
//		
		//logger.info("rankList : "+ rankList);
//		
//		for(int i =9;i>=0;i--) {
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			Member member = new Member();
//			member.setMember_idx((i+1)*2);
//			member.setNickname("유저 " + i);
//			
//			
//			map.put("dailywalk_idx", i+1);
//			map.put("member", member);
//			map.put("distance", 500 * (i+1));
//			map.put("regdate", "2023-03-29 11:52:31");
//			rankList.add(map);
//		}
		
		return rankList;
	}
	
	@GetMapping("/walk/weekly")
	public List getWeeklyWalkRanking(){
			
			//3단계
			List rankList = dailyWalkService.selectAllDailyWalkForWeek();
			
			return rankList;
	}
	
	@GetMapping("/walk/monthly")
	public List getMonthlyWalkRanking(){
		
		//3단계
		List rankList = dailyWalkService.selectAllDailyWalkForMonth();
		
		return rankList;
	}
	
	@GetMapping("/board/{period}/{boardName}")
	public List getBoardRanking(HttpServletRequest request, @PathVariable String period, @PathVariable String boardName){
		
		logger.info("period : " + period);
		logger.info("boardName : " + boardName);
		
		List rankList = boardRankService.selectAllBoardRank(boardName, period);
		logger.info("rankList : "+rankList);
		
		return rankList;
	}
	
	@PostMapping("/walk")
	public ResponseEntity<Message> regist(HttpServletRequest request, @RequestBody DailyWalk dailyWalk){
		
		//3단계
		dailyWalkService.regist(dailyWalk);
		
		Message message = new Message("일일 기록 등록 성공", 201);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		
		return entity;
	}
	
	@DeleteMapping("/walk")
	public ResponseEntity<Message> delete(HttpServletRequest request, @RequestBody DailyWalk dailyWalk){
		
		//3단계
		dailyWalkService.delete(dailyWalk.getMember().getMember_idx());
		
		Message message = new Message("일일 기록 삭제 성공", 200);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;
	}
}
