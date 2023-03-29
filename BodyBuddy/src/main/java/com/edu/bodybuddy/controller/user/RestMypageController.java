package com.edu.bodybuddy.controller.user;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.mypage.Ask;
import com.edu.bodybuddy.domain.mypage.Interest;
import com.edu.bodybuddy.domain.mypage.Mypost;
import com.edu.bodybuddy.domain.mypage.Report;
import com.edu.bodybuddy.domain.security.MemberDetail;
import com.edu.bodybuddy.exception.AskException;
import com.edu.bodybuddy.exception.MemberException;
import com.edu.bodybuddy.exception.ReportException;
import com.edu.bodybuddy.model.mypage.AskService;
import com.edu.bodybuddy.model.mypage.InterestService;
import com.edu.bodybuddy.model.mypage.MypostService;
import com.edu.bodybuddy.model.mypage.ReportService;
import com.edu.bodybuddy.util.Msg;
import com.edu.bodybuddy.util.MypageManager;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class RestMypageController {
    private Logger log = LoggerFactory.getLogger(getClass());
    private final AskService askService;
    private final ReportService reportService;
    private final MypostService mypostService;
    private final InterestService interestService;
    
    /*===================================================
    													내 글 보기
	===================================================*/
    @GetMapping("/mypost/list/{page}")
    public HashMap<String, Object> selectAll(@PathVariable int page){
    	//페이징처리를 위해 변수설정
    	int pageSize = 5;
    	int blockSize = 3;
    	int member_idx = getMember().getMember_idx();
    	int totalRecord = mypostService.selectTotal(getMember());
    	MypageManager paging = new MypageManager(totalRecord, page, pageSize, blockSize);
    	
    	//불러올 데이터 범위를 정해 DAO까지 전달
    	HashMap<String, Object> map = paging.getRange(page, pageSize); //조회에 사용할 범위와 멤버정보를 넣을 맵
    	map.put("member", getMember());
    	List<Mypost> list = mypostService.selectMypost(map);
    	log.info("최종 리스트는" + list);
    	
    	//반환할 맵을 만들어준다
    	HashMap<String, Object> resp = new HashMap();
    	//맵에 리스트 추가
    	resp.put("list", list);
    	//페이징처리 변수들을 담은 객체도 추가
    	resp.put("paging", paging);
    	
    	return resp;
    }
    
    /*===================================================
	    													찜 기능
	===================================================*/
    
    @PostMapping("/interest")
    public ResponseEntity<Msg> registInterest(Interest interest){
    	log.info("넘어온 객체 : "+interest);
    	interest.setMember_idx(getMember().getMember_idx());
    	interestService.regist(interest);
    	
    	Msg msg = new Msg("즐겨찾기 목록에 등록되었습니다");
        return new ResponseEntity<Msg>(msg, HttpStatus.OK);
    }

    @GetMapping("/interest/{page}")
    public HashMap<String, Object> getInterestList(@PathVariable int page){
        //페이징처리를 위해 변수설정
        int pageSize = 5;
        int blockSize = 5;
        int totalRecord = interestService.selectTotal(getMember());
        MypageManager paging = new MypageManager(totalRecord, page, pageSize, blockSize);

        //불러올 데이터 범위를 정해 DAO까지 전달
        HashMap<String, Object> map = paging.getRange(page, pageSize);
        map.put("member", getMember());
        List<Interest> list = interestService.getInterestList(map);
        //받아온 데이터와 총 레코드 수를 반환
        HashMap<String, Object> resp = new HashMap();
        resp.put("list", list);
        resp.put("paging", paging);

        return resp;
    }

    /*===================================================
                                                         문의글 관련
    ===================================================*/

    @GetMapping("/ask/list/{page}")
    public HashMap<String, Object> getAskList(@PathVariable int page){
    	//페이징처리를 위해 변수설정
    	int pageSize = 6;
    	int blockSize = 5;
    	int member_idx = getMember().getMember_idx();
    	int totalRecord = askService.getTotal(member_idx);
    	MypageManager paging = new MypageManager(totalRecord, page, pageSize, blockSize);
    	
    	//불러올 데이터 범위를 정해 DAO까지 전달
    	HashMap<String, Object> map = paging.getRange(page, pageSize);
    	map.put("member", getMember());
    	List<Ask> list = askService.getList(map);
    	//받아온 데이터와 총 레코드 수를 반환
    	HashMap<String, Object> resp = new HashMap();
    	resp.put("list", list);
    	resp.put("paging", paging);
    	
    	return resp;
    }

    @GetMapping("/ask/{ask_idx}")
    public Ask getAsk(@PathVariable int ask_idx){
        return askService.select(ask_idx);
    }


    @PostMapping("/ask")
    public ResponseEntity<Msg> registAsk(Ask ask){//문의글 등록
        //문의글을 등록한 유저 정보 취득
        ask.setMember(getMember());

        askService.insert(ask);
        
        Msg msg = new Msg("문의 글 등록이 완료되었습니다");
        return new ResponseEntity<Msg>(msg, HttpStatus.OK);
    }

    @PutMapping("/ask")
    public ResponseEntity<Msg> update(Ask ask){
    	log.info("수정요청" + ask);
        askService.update(ask);
        Msg msg = new Msg("문의 글이 수정되었습니다");
        return new ResponseEntity<Msg>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/ask")
    public ResponseEntity<Msg> delete(@RequestBody Ask ask){
        askService.delete(ask);
        Msg msg = new Msg("문의 글이 삭제되었습니다");
        return new ResponseEntity<Msg>(msg, HttpStatus.OK);
    }

     /*===================================================
                                                            신고글 관련
    ===================================================*/

    @GetMapping("/report/list/{page}")
    public HashMap<String, Object> getReportList(@PathVariable int page){
    	//페이징처리를 위해 변수설정
    	int pageSize = 6;
    	int blockSize = 5;
    	int member_idx = getMember().getMember_idx();
    	int totalRecord = reportService.selectTotal(member_idx);
    	MypageManager paging = new MypageManager(totalRecord, page, pageSize, blockSize);
    	
    	//불러올 데이터 범위를 정해 DAO까지 전달
    	HashMap<String, Object> map = paging.getRange(page, pageSize);
    	map.put("member", getMember());
    	List<Report> list = reportService.getList(map);
    	
    	//받아온 데이터와 총 레코드 수를 반환
    	HashMap<String, Object> resp = new HashMap();
    	resp.put("list", list);
    	resp.put("paging", paging);
    	
    	return resp;
    }
    
    @GetMapping("/report/{report_idx}")
    public Report getReport(@PathVariable int report_idx){
        return reportService.select(report_idx);
    }

	 @PostMapping("/report")
	 public ResponseEntity<Msg> registReport(Report report){//신고글 등록
	     //신고글을 등록한 유저 정보 취득
	     report.setMember(getMember());
	
	     reportService.regist(report);
	
	     Msg msg = new Msg("신고 글 등록이 완료되었습니다");
	     return new ResponseEntity<Msg>(msg, HttpStatus.OK);
	 }



    @PutMapping("/report")
    public ResponseEntity<Msg> update(Report report){
    	log.info("수정요청" + report);
        reportService.update(report);
        Msg msg = new Msg("신고 글이 수정되었습니다");
        return new ResponseEntity<Msg>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/report")
    public ResponseEntity<Msg> delete(@RequestBody Report report){
    	log.info("넘어온거 : "+report);
    	reportService.delete(report);
        Msg msg = new Msg("신고 글이 삭제되었습니다");
        return new ResponseEntity<Msg>(msg, HttpStatus.OK);
    }


    /*===================================================
                                                            예외처리
    ===================================================*/

    @ExceptionHandler({MemberException.class, AskException.class, ReportException.class})
    public ResponseEntity<Msg> handle(Exception e){
        Msg msg = new Msg();
        msg.setMsg(e.getMessage());
        ResponseEntity<Msg> entity = new ResponseEntity<Msg>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    /*===================================================
                                                            멤버 얻어오기
    ===================================================*/

    public Member getMember(){
//        MemberDetail memberDetail =(MemberDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return memberDetail.getMember();
//		아래는 디버깅 코드
        Member member = new Member();
        member.setMember_idx(32);
        return member;
    }

}
