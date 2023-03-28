package com.edu.bodybuddy.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.domain.diet.DietInfo;
import com.edu.bodybuddy.domain.diet.DietShare;
import com.edu.bodybuddy.domain.diet.DietShareComments;
import com.edu.bodybuddy.domain.diet.DietTip;
import com.edu.bodybuddy.domain.diet.DietTipComments;
import com.edu.bodybuddy.domain.diet.Food;
import com.edu.bodybuddy.domain.exr.ExrRoutine;
import com.edu.bodybuddy.domain.exr.ExrRoutineComment;
import com.edu.bodybuddy.exception.DietInfoException;
import com.edu.bodybuddy.exception.DietShareException;
import com.edu.bodybuddy.exception.DietTipException;
import com.edu.bodybuddy.model.diet.DietCategoryService;
import com.edu.bodybuddy.model.diet.DietInfoService;
import com.edu.bodybuddy.model.diet.DietShareCommentsService;
import com.edu.bodybuddy.model.diet.DietShareService;
import com.edu.bodybuddy.model.diet.DietTipCommentsService;
import com.edu.bodybuddy.model.diet.DietTipService;
import com.edu.bodybuddy.util.Msg;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;


@RestController
@RequestMapping("/rest/diet")
public class RestDietController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DietCategoryService dietCategoryService;
	
	@Autowired
	private DietInfoService dietInfoService;
	
	@Autowired
	private DietTipService dietTipService;
	
	@Autowired
	private DietShareService dietShareService;
	
	@Autowired
	private DietTipCommentsService dietTipCommentsService;
	
	@Autowired
	private DietShareCommentsService dietShareCommentsService;
	
	
	
	/*--------------------------------
			식단공유 페이지 관련 
	--------------------------------*/
	//글 목록 조회 
	@GetMapping("/share_list")
	public List<DietShare> getShareList(){
		List<DietShare> dietShareList=dietShareService.selectAll();
		
		return dietShareList;
	}
	
	//카테고리별 조회 
	@GetMapping("/share_list/category/{diet_category_idx}")
	public List<DietShare> getShareCategory(@PathVariable int diet_category_idx){
		List<DietShare> dietShareList=dietShareService.selectByIdx(diet_category_idx);
		
		return dietShareList;
	}
	
	//글 등록 
	@PostMapping("/share_regist")
	public ResponseEntity<Msg> shareRegist(DietShare dietShare){
		//logger.info("식단공유 글등록 작동중 "+dietShare);
			
		dietShareService.insert(dietShare);
		
		Msg msg=new Msg();
		msg.setMsg("글 등록 성공");
			
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);		
		return entity;
	}
	
	//글 수정
	@PutMapping("/share_edit")
	public ResponseEntity<Msg> shareEdit(@RequestBody DietShare dietShare) throws DietShareException{
		//logger.info("식단공유수정작동 "+dietShare);
		dietShareService.update(dietShare);
			
		Msg msg=new Msg();
		msg.setMsg("글 수정 완료");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;
	}
	
	//글 삭제
	@DeleteMapping("/share_del/{diet_share_idx}")
	public ResponseEntity<Msg> shareDel(@PathVariable int diet_share_idx) throws DietShareException{
		//logger.info("식단공유삭제작동 "+ diet_share_idx);
		dietShareService.delete(diet_share_idx);
			
		Msg msg=new Msg();
		msg.setMsg("글 삭제 완료");
			
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;	
	}
	
	//추천수 증가 
	@PutMapping("/share/recommend")
	public ResponseEntity<Msg>addRecommend(@RequestBody DietShare dietShare) throws DietShareException{
		//logger.info("추천수 작동 ");
		dietShareService.addRecommend(dietShare.getDiet_share_idx());
				
		Msg msg=new Msg();
		msg.setMsg("추천 되었습니다!");
				
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;	
	}
		
	//디테일페이지 불러오기(추천수 바로 반영하기 위해서)
	@GetMapping("/share_detail/{diet_share_idx}")
	public DietShare getPage(@PathVariable int diet_share_idx) {
		DietShare dietShare=(DietShare) dietShareService.select(diet_share_idx);
			
		return dietShare;
	}
	
	//검색 기능(제목)
	@GetMapping("/share/search")
	public List<DietShare> shareSearch(String keyword){
		//logger.info("검색기능작동 ");
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("keyword", keyword);
			
		List<DietShare> dietShareList=dietShareService.selectBySearch(map);
			
		return dietShareList;
	}
	
	//댓글 목록 
	@GetMapping("/share/comments/{diet_share_idx}")
	public List<DietShareComments> getShareCommentsList(@PathVariable int diet_share_idx){
		return dietShareCommentsService.selectByIdx(diet_share_idx);
	}
		
	//댓글등록 
	@PostMapping("/share/comments/regist")
	public ResponseEntity<Msg> registShareComments(DietShareComments dietShareComments){
		dietShareCommentsService.insert(dietShareComments);
			
		Msg msg=new Msg();
		msg.setMsg("댓글 등록 완료");
			
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
		
	//댓글삭제 
	@DeleteMapping("/share/comments/{diet_share_comments_idx}")
	public ResponseEntity<Msg> delShareComments(@PathVariable int diet_share_comments_idx, HttpServletRequest request){
		dietShareCommentsService.delete(diet_share_comments_idx);
			
		Msg msg=new Msg();
		msg.setMsg("댓글 삭제 완료");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	
	
	/*--------------------------------
			식단팁 페이지 관련 
	--------------------------------*/
	//글 목록 가져오기 
	@GetMapping("/tip_list")
	public List<DietTip> getTipList(){ //int page
		//logger.info("rest작동");
		//List<DietTip> dietTipList=dietTipService.selectAllPage(page);
		
		List<DietTip> dietTipList=dietTipService.selectAll();
		return dietTipList;
	}	
	
	//글 등록 
	@PostMapping("/tip_regist")
	public ResponseEntity<Msg> tipRegist(DietTip dietTip){
		//logger.info("식단팁 글등록 작동중 "+dietTip);
		
		dietTipService.insert(dietTip);
	
		Msg msg=new Msg();
		msg.setMsg("글 등록 성공");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);		
		return entity;
	}
	
	//글 수정
	@PutMapping("/tip_edit")
	public ResponseEntity<Msg> tipEdit(@RequestBody DietTip dietTip) throws DietTipException{
		//logger.info("식단팁수정작동 "+dietTip);
		dietTipService.update(dietTip);
			
		Msg msg=new Msg();
		msg.setMsg("글 수정 완료");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;
	}
	
	//글 삭제
	@DeleteMapping("/tip_del/{diet_tip_idx}")
	public ResponseEntity<Msg>tipDel(@PathVariable int diet_tip_idx) throws DietTipException{
		//logger.info("식단팁삭제작동 "+ diet_tip_idx);
		dietTipService.delete(diet_tip_idx);
			
		Msg msg=new Msg();
		msg.setMsg("글 삭제 완료");
			
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;	
	}
	
	//추천수 증가 
	@PutMapping("/tip/recommend")
	public ResponseEntity<Msg>addRecommend(@RequestBody DietTip dietTip) throws DietTipException{
		//logger.info("추천수 작동 ");
		dietTipService.addRecommend(dietTip.getDiet_tip_idx());
			
		Msg msg=new Msg();
		msg.setMsg("추천 되었습니다!");
			
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg,HttpStatus.OK);	
		return entity;	
	}
	
	//디테일페이지 불러오기(추천수 바로 반영하기 위해서)
	@GetMapping("/tip_detail/{diet_tip_idx}")
	public DietTip detailPage(@PathVariable int diet_tip_idx) {
		DietTip dietTip=(DietTip) dietTipService.select(diet_tip_idx);
		
		return dietTip;
	}
	
	//검색 기능(제목)
	@GetMapping("/tip/search")
	public List<DietTip> tipSearch(String keyword){
		logger.info("검색기능작동 ");
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("keyword", keyword);
		
		List<DietTip> dietTipList=dietTipService.selectBySearch(map);
		
		return dietTipList;
	}
	
	//댓글 목록 
	@GetMapping("/tip/comments/{diet_tip_idx}")
	public List<DietTipComments> getTipCommentsList(@PathVariable int diet_tip_idx){
		return dietTipCommentsService.selectByIdx(diet_tip_idx);
	}
	
	//댓글등록 
	@PostMapping("/tip/comments/regist")
	public ResponseEntity<Msg> registTipComments(DietTipComments dietTipComments){
		dietTipCommentsService.insert(dietTipComments);
		
		Msg msg=new Msg();
		msg.setMsg("댓글 등록 완료");
		
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}
	
	//댓글삭제 
	@DeleteMapping("/tip/comments/{diet_tip_comments_idx}")
	public ResponseEntity<Msg> delTipComments(@PathVariable int diet_tip_comments_idx, HttpServletRequest request){
		dietTipCommentsService.delete(diet_tip_comments_idx);
		
		Msg msg=new Msg();
		msg.setMsg("댓글 삭제 완료");
		ResponseEntity<Msg> entity=new ResponseEntity<Msg>(msg, HttpStatus.OK);
		return entity;
	}

	
	/*--------------------------------
			칼로리계산기 페이지 관련 
	--------------------------------*/
	@GetMapping("/kcal/list")
	public List<Food> getFoodList() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/
        
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=QjMliKoiQ9gSagmSAFaEQ3xJNMR8F1%2FpOcKLAhXvXFhWAVjqHaRjeYDlPw%2BQWJwmTD0ZMJF407DnUpER3k8o3g%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //1~220
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8"));/*한 페이지 결과 수*/  
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) Default: xml*/      
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        rd.close();
        conn.disconnect();
        
        String data=sb.toString();
		
        //json 문자열을 대상으로 파싱해서 원하는 결과 가공하기
        JSONParser jsonParser=new JSONParser();
        List<Food> foodList=new ArrayList<Food>();
        try {
            JSONObject json=(JSONObject)jsonParser.parse(data);

            JSONObject body = (JSONObject)json.get("body");
            JSONArray items=(JSONArray)body.get("items");

            for(int i=0; i<items.size(); i++) {
                JSONObject itemLists=(JSONObject)items.get(i);

                String name=(String)itemLists.get("DESC_KOR");
                String wt=(String)itemLists.get("SERVING_WT");
                String kcal=(String)itemLists.get("NUTR_CONT1");
                String car=(String)itemLists.get("NUTR_CONT2");
                String tien=(String)itemLists.get("NUTR_CONT3");
                String vince=(String)itemLists.get("NUTR_CONT4");
                
                Food food=new Food();
                food.setDESC_KOR(name);
                food.setSERVING_WT(wt);
                food.setNUTR_CONT1(kcal);
                food.setNUTR_CONT2(car);
                food.setNUTR_CONT3(tien);
                food.setNUTR_CONT4(vince);
                
                foodList.add(food);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return foodList;
        
    }
		
	
	
	
	
}
