package com.edu.bodybuddy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

	//예외처리 할것이 많은데, 하나로 IOException처리....이게 맞나? 나중에 세분화할 필요가 있으면 세분화하겠음
	public void getWeatherResponse() throws IOException {
		System.out.println(this.getWeatherServiceKey());
		//아래부분은 거의 고정값
        StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
        urlBuilder.append("?" + "serviceKey" + "=" +weatherServiceKey); /*Service Key*/
        urlBuilder.append("&" + "pageNo" + "=" + pageNo); /*페이지번호*/
        urlBuilder.append("&" + "numOfRows" + "=" + numOfRows); /*한 페이지 결과 수*/
        urlBuilder.append("&" + "dataType" + "=" + dataType); /*요청자료형식(XML/JSON) Default: XML*/
        
        //아래는 변수로 받아주어야 함 (URLEncoder꼭 써야할까?)
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20230322", "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
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
        refinedata(sb.toString());
        
        //System.out.println(sb.toString());
	}
	
	//강제되는 예외처리는 어떻게 처리해야하나..
	public void refinedata(String rawData) {
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			//readValue로 자바객체로 만들어주면 더이상 json이 아님
			/*
			HashMap<String, JsonObject> map=objectMapper.readValue(rawData, HashMap.class); 
			JsonObject responseData=map.get("response");
			
			System.out.println(responseData.toString());
			*/
			JsonNode jsonNode=objectMapper.readTree(rawData);
			JsonNode response=jsonNode.get("response");
			WeatherAPIObject weatherAPIObject =objectMapper.readValue(response.toString(), WeatherAPIObject.class);
			
			String resultCode=weatherAPIObject.getHeader().getResultCode();
			if(Integer.parseInt(resultCode)==00) {
				logger.info("정상적인 결과입니다.");
				successResultCode(weatherAPIObject);
			}else {
				logger.warn("잘못된 결과입니다. 결과코드는 :"+resultCode);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void successResultCode(WeatherAPIObject weatherAPIObject) {
		List<Item> realData  =weatherAPIObject.getBody().getItems().getItem();
		logger.info("받아온 데이터의 크기는"+realData.size());

		//현재 날짜에서 값 가져오기
		LocalDate date=LocalDate.now();
		String today=date.toString().replace("-",""); //특정문자제거
		
		logger.info(today);
		logger.info(realData.get(0).getFcstDate());
		
		for(int i=0; i<realData.size(); i++) {
			
			//출력하고자 하는 날짜와 일치하면
			if(realData.get(i).getFcstDate().equals(today)) {
				String category=realData.get(i).getCategory();
				//logger.info(category);
				if(category.equals("SKY")) {

					switch(realData.get(i).getFcstValue()) {
						case "1":System.out.println("오늘"+realData.get(i).getFcstTime()+" 맑음");break;
						case "2":System.out.println("오늘"+realData.get(i).getFcstTime()+"흐림");break;
						case "3":System.out.println("오늘"+realData.get(i).getFcstTime()+"구름많음");break;
						case "4":System.out.println("오늘"+realData.get(i).getFcstTime()+"비옴");break;
					}
					
				}else if(category.equals("TMP")){
					System.out.println("오늘"+realData.get(i).getFcstTime()+"온도는 "+realData.get(i).getFcstValue());
				}

			}
		}
			
	}
	
}
