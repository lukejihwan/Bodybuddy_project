package com.edu.bodybuddy.controller.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.bodybuddy.domain.myrecord.DailyWalk;
import com.edu.bodybuddy.domain.myrecord.DietRecord;
import com.edu.bodybuddy.domain.myrecord.ExrRecord;
import com.edu.bodybuddy.domain.myrecord.GpsData;
import com.edu.bodybuddy.domain.myrecord.PhysicalRecord;
import com.edu.bodybuddy.exception.DietRecordException;
import com.edu.bodybuddy.exception.ExrDetailRecordException;
import com.edu.bodybuddy.exception.ExrRecordException;
import com.edu.bodybuddy.exception.PhysicalRecordException;
import com.edu.bodybuddy.model.myrecord.DailyWalkService;
import com.edu.bodybuddy.model.myrecord.DietRecordService;
import com.edu.bodybuddy.model.myrecord.ExrRecordService;
import com.edu.bodybuddy.model.myrecord.GpsDataService;
import com.edu.bodybuddy.model.myrecord.MyRecordService;
import com.edu.bodybuddy.model.myrecord.PhysicalRecordService;
import com.edu.bodybuddy.util.Message;
import com.edu.bodybuddy.util.WeatherAPIManager;

@RestController
@RequestMapping("/rest/myrecord")
public class RestMyRecordController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MyRecordService myRecordService;
	
	@Autowired
	private PhysicalRecordService physicalRecordService;
	
	@Autowired
	private ExrRecordService exrRecordService;
	
	@Autowired
	private DietRecordService dietRecordService;
	
	@Autowired
	private GpsDataService gpsDataService;
	
	@Autowired
	private DailyWalkService dailyWalkService;

	/*================================================
	 * =================안드로이드 google API영역=================
	 * */
	
	// 안드로이드에서 전송한 GPSData 정보를 받는 메서드
	@PostMapping("/today/gps")
	public void GPSDatafromAndroid(HttpServletRequest request,@RequestBody List<GpsData> gpsList){
		logger.info("응답 받음");
		logger.info("받은 데이터의 모습!"+gpsList);
		
		LocalDate currentDate=LocalDate.now();
		logger.info("오늘 날짜는"+currentDate);
		
		
		//GpsData insert!
		for(int i=0; i<gpsList.size(); i++) {
			GpsData gpsData=gpsList.get(i);
			
			// 오늘 날짜 같이 넣기!
			gpsData.setRegdate(currentDate.toString());
			gpsDataService.insert(gpsData);
		}
		
		logger.info("위치데이터 입력 성공");
	}
	
	
	// 해당 날짜에 대한 위도 경도 값을 가져오는 함수!
	@GetMapping("/today/gps")
	public List getGPSData() {
		logger.info("여기까지 왔니");
		List<GpsData>gpsList=gpsDataService.selectForDay("2023-03-24 00:00:00");
		return gpsList;
	}
	
	/*지환 영역 시작*/
	//한달간의 러닝기록을 불러오는 함수!
	@PostMapping("/runningRecord")
	public List getRunningRecord(@RequestBody Map<String, String> runningOneMonthPeriod) {
		
		logger.info("받아온 첫날 :"+runningOneMonthPeriod.get("firstDay")+" 마지막날은"+runningOneMonthPeriod.get("lastDay"));
		Set runningListForMonth=gpsDataService.selectRunningForMonth(runningOneMonthPeriod);
		
		//생성된 Set배열을 List형식으로 변환
		List<String> runningRegdateForMonth=new ArrayList<>(runningListForMonth);
		
		return runningRegdateForMonth;
	}
	
	/*지환 영역 끝*/
	
	/*=========================================
	 * ==============날씨 API 영역====================
	 * */
	
	@GetMapping("/weatherAPI/{nx}/{ny}")
	public Map<String, String> getWeather(@PathVariable(name="nx") int nx, @PathVariable(name="ny") int ny) {
		
		logger.info("받아온 nx값은 : "+nx);
		logger.info("받아온 ny값은 : "+ny);
		
		Map<String, String> dataForResponseMap=myRecordService.getWeather(nx, ny);
		return dataForResponseMap;
	}
	
	/*==============================================
	 * =======================신체기록==================
	 * =============================================*/
	
	//신체기록을 등록하는 메서드
	@PostMapping("/physicalRecord")
	public ResponseEntity<Message> postPhysicalRecord(@RequestBody PhysicalRecord physicalRecord) {
		
		logger.info("받아온 몸무게는"+physicalRecord.getWeight());
		logger.info("받아온 bmi는"+physicalRecord.getBmi());
		logger.info("받아온 체지방은"+physicalRecord.getBodyFat());
		logger.info("받아온 골격근량은"+physicalRecord.getMusclemass());
		logger.info("받아온 날짜는"+physicalRecord.getRegdate());
		//service 일시키기
		physicalRecordService.regist(physicalRecord);
		
		Message message=new Message();
		message.setMsg("신체기록 등록 성공");
		return new ResponseEntity<Message>(message ,HttpStatus.OK);
	}
	
	//해당월의 신체기록을 불러오는 메서드
	@PostMapping("/physicalListForMonth")
	public List<PhysicalRecord> getPhysicalRecordForMonth(@RequestBody Map<String, String> pysicalOneMonthPeriod){
		logger.info("한달동안의 신체기록을 불러올 첫날과 마지막 날 값은 :" +pysicalOneMonthPeriod.get("firstDay")+",,"+pysicalOneMonthPeriod.get("lastDay"));
		List<PhysicalRecord> physicalList=physicalRecordService.selectForMonth(pysicalOneMonthPeriod);
		return physicalList;
	}
	
	@GetMapping("/physicalRecord")
	public PhysicalRecord getPhysicalRecord(@RequestParam("regdate") String regdate, @RequestParam("member_idx") int member_idx) {
		PhysicalRecord physicalRecord=new PhysicalRecord();
		physicalRecord.setRegdate(regdate);
		physicalRecord.setMember_idx(member_idx);
		//서비스 호출하기
		physicalRecord= physicalRecordService.select(physicalRecord);
		
		return physicalRecord;
	}
	
	@PutMapping("/physicalRecord")
	public ResponseEntity<Message> updatePhysicalRecord(@RequestBody PhysicalRecord physicalRecord){
		
		logger.info("클라이언트로부터 받아온 member_idx 값은"+physicalRecord.getMember_idx());
		logger.info("클라이언트로부터 받아온 regdate 값은"+physicalRecord.getRegdate());
		logger.info("클라이언트로부터 받아온 BMI 값은 :"+physicalRecord.getBmi());
		logger.info("클라이언트로부터 받아온 getBodyFat :"+physicalRecord.getBodyFat());
		logger.info("클라이언트로부터 받아온 getHeight 값은 :"+physicalRecord.getHeight());
		logger.info("클라이언트로부터 받아온 getMusclemass 값은 :"+physicalRecord.getMusclemass());
		logger.info("클라이언트로부터 받아온 체중 값은 :"+physicalRecord.getWeight());
		
		//service 일시키기
		physicalRecordService.update(physicalRecord);
		
		Message message=new Message();
		message.setCode(200);
		message.setMsg("신체기록 수정 성공");
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message,HttpStatus.OK);
		
		return entity;
	}
	
	@DeleteMapping("/physicalRecord")
	public ResponseEntity<Message> deletePhysicalRecord(@RequestBody PhysicalRecord physicalRecord){
		//아래의 방식으로 사용해도 됨 (선택의 문제)
		//RequestParam("regdate") String regdate, RequestParam("member_idx") int member_idx
		
		logger.info("받아온 member_idx 값은"+physicalRecord.getMember_idx());
		logger.info("받아온 regdate 값은"+physicalRecord.getRegdate());
		
		physicalRecordService.delete(physicalRecord);
		
		Message message=new Message();
		message.setCode(200);
		message.setMsg("신체기록 삭제 완료");
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK);
		
		logger.info("신체기록 삭제하기 전달");
		
		return entity;
	}
	
	/*=============================================
	 * ===================운동기록영역=====================
	 * =============================================*/
	
	//한달간의 기록을 보여주는 메서드
	//ResponseBody 를 붙일 필요없음, RestController로 클래스 선언이 되어있기 때문에
	@PostMapping("/exrListForMonth")
	public List<ExrRecord> getExrRecordForMonth(@RequestBody Map<String, String> oneMonthPeriod){
		logger.info("한달동안의 기록을 불러올 첫날과 마지막 날 값은 :" +oneMonthPeriod.get("firstDay")+",,"+oneMonthPeriod.get("lastDay"));
		//LocalDate  currentDate=LocalDate.now();
		//logger.info("오늘 날짜는"+currentDate);
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
	
	@PostMapping("/exrRecord")
	public ResponseEntity<Message> updateExrRecord(@RequestParam("exrname") String exrname, 
																			@RequestParam("exr_idx") int exr_idx, 
																			@RequestParam("kgs[]") List<Integer> kgList, 
																			@RequestParam("times[]") List<Integer> timesList){
		
		
		/*
		logger.info(exrname);
		logger.info("받아온 1번째 kgs[]의 값은 :"+kgList.get(0));
		logger.info("받아온 2번째 kgs[]의 값은 :"+kgList.get(1));
		logger.info("받아온 3번째 kgs[]의 값은 :"+kgList.get(2));
		logger.info("받아온 첫번째 times[]의 값은 : :"+timesList.get(0));
		*/
		//서비스 일시키기
		exrRecordService.update(exrname, exr_idx, kgList, timesList);
		
		Message message=new Message();
		message.setMsg("수정되었습니다");
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
	//운동기록을 삭제하는 메서드
	@DeleteMapping("/exrRecord/{exr_record_idx}")
	public ResponseEntity<Message> delExrRecord(@PathVariable(name="exr_record_idx") int exr_record_idx){
		logger.debug("받아온 삭제할 idx값은"+exr_record_idx);
		exrRecordService.delete(exr_record_idx);
		Message message=new Message();
		message.setMsg("삭제되었습니다");
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
	
	
	
	
	/*=============================================
	 * =====================식단기록 영역=================
	 * */
	
	@PostMapping("/dietAPIRecord")
	public List getDietAPIRecord(@RequestBody Map<String, String> foodName){
		logger.info("여기는 옴");
		logger.info("받아온 음식이름은"+foodName);
		List dietAPIRecord=dietRecordService.getDietAPIRecord(foodName.get("foodName"));
		
		return dietAPIRecord;
	}
	
	@PostMapping("/dietListForMonth")
	public List<DietRecord> getDietRecordForMonth(@RequestBody Map<String, String> oneMonthPeriod){
		logger.info("한달동안의 식단기록을 불러올 첫날과 마지막 날 값은 :" +oneMonthPeriod.get("firstDay")+",,"+oneMonthPeriod.get("lastDay"));
		
		List<DietRecord> dietRecordListMonth=dietRecordService.selectForMonth(oneMonthPeriod);
		logger.info("한달간의 식단기록 불러온 결과의 갯수는 : "+dietRecordListMonth.size());
		return dietRecordListMonth;
	}
	
	@PutMapping("/dietRecord")
	public ResponseEntity<Message> getDietRecordForDay(@RequestBody DietRecord dietRecord) {
		logger.info("받아온 수정할 member_idx값은"+dietRecord.getMember_idx());
		
		//서비스 호출
		dietRecordService.update(dietRecord);
		
		Message message=new Message();
		message.setCode(200);
		message.setMsg("식단기록 수정 성공");
		
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;
	}
	
	@DeleteMapping("/dietRecord")
	public ResponseEntity<Message> deleteDietRecord(@RequestBody DietRecord dietRecord){
		logger.info("삭제할 diet_idx값은"+dietRecord.getDiet_idx());
		
		//서비스 호출
		dietRecordService.delete(dietRecord);
		
		Message message=new Message();
		message.setCode(200);
		message.setMsg("식단기록 삭제 성공");
		
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;
	}
	
	
	@PostMapping("/dietRecord")
	public ResponseEntity<Message> registDietRecord(@RequestBody DietRecord dietRecord){
		
		dietRecordService.regist(dietRecord);
		Message message=new Message();
		message.setCode(200);
		message.setMsg("식단기록 등록 성공");
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK); 
		
		return entity;
	}
	
	@GetMapping("/dietRecord/{regdate}/{member_idx}")
	public List getDietRecord(@PathVariable("regdate") String regdate, @PathVariable("member_idx") int member_idx) {
		logger.info("받아온 member_idx는"+member_idx);
		
		DietRecord dietRecord=new DietRecord();
		dietRecord.setRegdate(regdate);
		dietRecord.setMember_idx(member_idx);
		//대체해서 반환
		List dietRecordList=dietRecordService.select(dietRecord);
		
		return dietRecordList;
	}
	
	/*=======================================
	 * ===============테스트영역 시작==================
	 * */
	//테스트용
		@GetMapping("/dailyRecord")
		public List select() {
			
			List<DailyWalk> list= dailyWalkService.selectAllDailyWalkForMonth();
			logger.info("받아온 운동닉네임은"+list.get(0).getMember().getNickname());
			
			return null;
		}
		
	/*=======================================
	 * ===============테스트영역 끝==================
	 * */
	
	
	
	//이 예외처리를 나중에 controllerAdvice로 처리해줄지 아니면 이렇게 처리해줄지는 나중에 보자
	//예외를 처리하는 전역적 ExceptionHandler
	@ExceptionHandler({ExrRecordException.class, ExrDetailRecordException.class, PhysicalRecordException.class, DietRecordException.class})
	public ResponseEntity<Message> handle(RuntimeException e){
		
		Message message=new Message();
		message.setMsg(e.getMessage());
		
		//임시로 BAD_REQUEST로 설정해 놓음
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		
		return entity;
	}

}





