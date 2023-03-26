package com.edu.bodybuddy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class DietAPIManager {
	private String dietServiceKey;
	private String dataType;
	
		public Map<String, String> getDietAPIContent(String foodName) throws UnsupportedEncodingException, IOException{
	        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+dietServiceKey); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("desc_kor","UTF-8") + "=" + URLEncoder.encode(foodName, "UTF-8")); /*식품이름*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("bgn_year","UTF-8") + "=" + URLEncoder.encode("2017", "UTF-8")); /*구축년도*/
	        //urlBuilder.append("&" + URLEncoder.encode("animal_plant","UTF-8") + "=" + URLEncoder.encode("(유)돌코리아", "UTF-8")); /*가공업체*/
	        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + dataType); /*응답데이터 형식(xml/json) Default: xml*/
	        
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
	        System.out.println(sb.toString());
	        
	        return null;
		}
}
