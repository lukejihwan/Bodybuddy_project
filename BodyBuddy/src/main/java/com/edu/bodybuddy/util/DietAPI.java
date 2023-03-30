package com.edu.bodybuddy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.edu.bodybuddy.domain.diet.Food;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class DietAPI {
	private String dietServiceKey;
	private String dataType;
	
	//리스트 전부 조회
	public List getDietAPIList(String foodName) throws IOException{
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/       
	    urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+dietServiceKey); /*Service Key*/
	    //urlBuilder.append("&" + URLEncoder.encode("desc_kor","UTF-8") + "=" + URLEncoder.encode(foodName, "UTF-8")); /*식품이름*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //1~220
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8"));/*한 페이지 결과 수*/  
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + dataType); /*응답데이터 형식(xml/json) Default: xml*/ /*응답데이터 형식(xml/json) Default: xml*/      
        
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

	//음식이름 키워드 조회
	public List getDietAPISearch(String foodName) throws IOException{
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+dietServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("desc_kor","UTF-8") + "=" + URLEncoder.encode(foodName, "UTF-8")); /*식품이름*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
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
        String data=sb.toString();
		
        //객체 담기
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> dietMap=new HashMap<String, Object>();
        dietMap=objectMapper.readValue(sb.toString(),new TypeReference<Map<String, Object>>(){});
        
        Map<String, Object> foodMap=(Map<String, Object>) dietMap.get("body");
        
        System.out.println(foodMap.get("items"));
        
        List foodList=(List)foodMap.get("items");
        
        return foodList;
	}





}
