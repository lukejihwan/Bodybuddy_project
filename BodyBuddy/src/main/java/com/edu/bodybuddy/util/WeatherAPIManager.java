package com.edu.bodybuddy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.edu.bodybuddy.domain.myrecord.Item;
import com.edu.bodybuddy.domain.myrecord.WeatherAPIObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
@Component
public class WeatherAPIManager {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	private String weatherServiceKey;
	private String pageNo;
	private String numOfRows;
	private String dataType;
	private String nx;
	private String ny;

	//예외처리 할것이 많은데, 하나로 IOException처리....이게 맞나? 나중에 세분화할 필요가 있으면 세분화하겠음
	public Map<String, String> getWeatherResponse(int nx,int ny) throws IOException {
		Map<String, String> dataForResponseMap=null;
		System.out.println(this.getWeatherServiceKey());
		//아래부분은 거의 고정값
        StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
        urlBuilder.append("?" + "serviceKey" + "=" +weatherServiceKey); /*Service Key*/
        urlBuilder.append("&" + "pageNo" + "=" + pageNo); /*페이지번호*/
        urlBuilder.append("&" + "numOfRows" + "=" + numOfRows); /*한 페이지 결과 수*/
        urlBuilder.append("&" + "dataType" + "=" + dataType); /*요청자료형식(XML/JSON) Default: XML*/
        
        //오늘 하루전 날짜의 0200시 데이터를 불러오도록, 그래야 그다음날인
        //오늘 데이터를 전체 업로드 할 수가 있음
        LocalDate date=LocalDate.now();
        String yesterday=date.minusDays(1).toString().replace("-",""); //특정문자제거
        
        //아래는 변수로 받아주어야 함 (URLEncoder꼭 써야할까?)
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(yesterday, "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("2300", "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(Integer.toString(nx), "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(Integer.toString(ny), "UTF-8")); /*예보지점의 Y 좌표값*/
        System.out.println(urlBuilder.toString());
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        logger.info("Response code: " + conn.getResponseCode());
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

        //데이터 가공 메서드를 따로 빼자
        dataForResponseMap=refinedata(sb.toString());
        
        //System.out.println(sb.toString());
        return dataForResponseMap;
	}
	
	//강제되는 예외처리는 어떻게 처리해야하나..
	public Map<String, String> refinedata(String rawData) {
		ObjectMapper objectMapper=new ObjectMapper();
		Map<String, String> dataForResponseMap=null;
		try {
			JsonNode jsonNode=objectMapper.readTree(rawData);
			JsonNode response=jsonNode.get("response");
			WeatherAPIObject weatherAPIObject =objectMapper.readValue(response.toString(), WeatherAPIObject.class);
			
			String resultCode=weatherAPIObject.getHeader().getResultCode();
			if(Integer.parseInt(resultCode)==00) {
				logger.info("정상적인 결과입니다.");
				
				dataForResponseMap=successResultCode(weatherAPIObject);
			}else {
				logger.warn("잘못된 결과입니다. 결과코드는 :"+resultCode);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataForResponseMap;
	}
	
	public Map<String, String> successResultCode(WeatherAPIObject weatherAPIObject) {
		 //최종데이터를 모으는 List
		//List<Map<String, String>> finalDataForResponseList=null;
		//맵만 보내면되나, 맵을 담은 리스트를 보낼 필요는 없는거 맞겠지?
		Map<String, String> dataForResponseMap=new HashMap<String, String>();
		
		//공통으로쓰일 평균구할때 사용될 변수
		int numforAverage=0;
		
		//하늘상태 평균을 내기위한 변수
		int totalSkyData=0;
		int averageSkyData=0;
		
		//평균온도를 구하기 위한 변수
		int totalTemp=0;
		float averageTemp=0;
		
		//습도를 구하기 위한 변수
		int totalHumidityData=0;
		int averageHumidity=0;
		
		//평균풍속을 구하기 위한 변수
		float totalWindSpeedData=0;
		float averageWindSpeed=0;
		
		//평균강수확률을 구하기 위한 변수
		int totalPercentForPrecipitaion=0;
		int averagePrecipitationPercent=0;
		
		List<Item> realData  =weatherAPIObject.getBody().getItems().getItem();
		logger.info("받아온 데이터의 크기는"+realData.size());

		//현재 날짜에서 값 가져오기
		LocalDate date=LocalDate.now();
		String today=date.toString().replace("-",""); //특정문자제거
		
		logger.info(today);
		logger.info(realData.get(0).getFcstDate());
		
		for(int i=0; i<realData.size(); i++) {
			
			//출력하고자 하는 날짜와 일치하면
			if(		realData.get(i).getFcstDate().equals(today) && (
					realData.get(i).getFcstTime().equals("0700") ||
					realData.get(i).getFcstTime().equals("0800") ||
					realData.get(i).getFcstTime().equals("0900") ||
					realData.get(i).getFcstTime().equals("1000") ||
					realData.get(i).getFcstTime().equals("1100") ||
					realData.get(i).getFcstTime().equals("1200") ||
					realData.get(i).getFcstTime().equals("1300") ||
					realData.get(i).getFcstTime().equals("1400") ||
					realData.get(i).getFcstTime().equals("1500") ||
					realData.get(i).getFcstTime().equals("1600") ||
					realData.get(i).getFcstTime().equals("1700"))) {
				
				String category=realData.get(i).getCategory();
				//logger.info(category);
				if(category.equals("SKY")) {
					//평균을 내기위해 나온 시간 만큼 쌓기(여기 넣는 이유는 한 시간대에 한번만 찍혀야 하므로)
					numforAverage+=1;
					
					logger.info("하늘상태 나오는 곳=================");
					switch(realData.get(i).getFcstValue()) {
						case "1":
							System.out.println("오늘"+realData.get(i).getFcstTime()+" 맑음");
							totalSkyData+=Integer.parseInt(realData.get(i).getFcstValue());
							break;
							
						case "2":
							System.out.println("오늘"+realData.get(i).getFcstTime()+"조금흐림");
							totalSkyData+=Integer.parseInt(realData.get(i).getFcstValue());
							break;
							
						case "3":
							System.out.println("오늘"+realData.get(i).getFcstTime()+"구름많음");
							totalSkyData+=Integer.parseInt(realData.get(i).getFcstValue());
							break;
						
						case "4":
							System.out.println("오늘"+realData.get(i).getFcstTime()+"흐림");
							totalSkyData+=Integer.parseInt(realData.get(i).getFcstValue());
							break;
					}
					
				}else if(category.equals("TMP")){
					//logger.info("오늘"+realData.get(i).getFcstTime()+"온도는 "+realData.get(i).getFcstValue());
					//오늘날짜 00~24시까지의 평균기온을 계산해주겠음 (1시간에 한 데이터)
					logger.info("온도 나오는 곳=================");
					totalTemp+=Integer.parseInt(realData.get(i).getFcstValue());
					System.out.println("오늘"+realData.get(i).getFcstTime()+"::"+Integer.parseInt(realData.get(i).getFcstValue()));
					
				}else if(category.equals("REH")) {
					logger.info("습도 나오는 곳=================");
					totalHumidityData+=Integer.parseInt(realData.get(i).getFcstValue());
					System.out.println("오늘"+realData.get(i).getFcstTime()+"::"+realData.get(i).getFcstValue());
					
					
				}else if(category.equals("VEC")) {
					logger.info("풍향 나오는 곳================="); //풍향은 일단 무시하자
					System.out.println("오늘"+realData.get(i).getFcstTime()+"::"+realData.get(i).getFcstValue());
					
				}else if(category.equals("WSD")) {
					logger.info("풍속 나오는 곳=================");
					totalWindSpeedData+=Float.parseFloat(realData.get(i).getFcstValue());
					System.out.println("오늘"+realData.get(i).getFcstTime()+"::"+realData.get(i).getFcstValue());
					
				}else if(category.equals("POP")) {
					logger.info("강수확률 나오는 곳=================");
					totalPercentForPrecipitaion+=Integer.parseInt(realData.get(i).getFcstValue());
					System.out.println("오늘"+realData.get(i).getFcstTime()+"::"+realData.get(i).getFcstValue());
					
				}
			}
		}
		//평균하늘상태 구하는 공식=======================
		averageSkyData=totalSkyData/numforAverage;
		
		//평균기온 구하는 공식=======================
		averageTemp=(float)totalTemp/numforAverage;
		logger.info("시간대의 갯수는 :"+numforAverage);
		logger.info("오전7시부터 오후6시까지 평균 온도는: "+(Math.round(averageTemp*10)/10.0));
		
		//평균습도 구하는 공식=====================================
		averageHumidity=totalHumidityData/numforAverage;
		
		//평균풍속 구하는 공식=======================================
		averageWindSpeed=((float)totalWindSpeedData/numforAverage);

		//평균강수확률 구하는 공식=======================================
		averagePrecipitationPercent=totalPercentForPrecipitaion/numforAverage;
		
		dataForResponseMap.put("skyData", Integer.toString(averageSkyData));
		dataForResponseMap.put("tempData", String.format("%.1f", averageTemp));
		dataForResponseMap.put("humidityData", Integer.toString(averageHumidity));
		dataForResponseMap.put("windSpeedData", String.format("%.1f",averageWindSpeed));
		dataForResponseMap.put("precipitaionData", Integer.toString(averagePrecipitationPercent));
		
		logger.info("바깥 날씨의 최종결과는 : "+dataForResponseMap.get("skyData"));
		
		//finalDataForResponseList.add();
		return dataForResponseMap;
		
	}
	
}
