package com.edu.bodybuddy.util;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class NaverMapAPIManager {
	private String geocoding;
	private String reverseGeocoding;
	private String search;
	private String naverMapId;
	private String naverMapSecret;
	private String naverSearchId;
	private String naverSearchSecret;
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	public static final String NAVER_MAP_ID_PARAM = "X-NCP-APIGW-API-KEY-ID";
	public static final String NAVER_MAP_SECRET_PARAM = "X-NCP-APIGW-API-KEY";
	public static final String NAVER_SEARCH_ID_PARAM = "X-Naver-Client-Id";
	public static final String NAVER_SEARCH_SECRET_PARAM = "X-Naver-Client-Secret";
	
	
	
	/**
	 * @param coords lattitude,longitude 형태의 문자열
	 * @return map으로 반환된 double형 좌표값, key는 lat, lon이다
	 */
	public HashMap<String, Double> parseCoords(String coords){
		
		String[] split = coords.split(",");
		
		HashMap<String, Double> map = new HashMap<String, Double>();
		map.put("lat", Double.parseDouble(split[0]));
		map.put("lon", Double.parseDouble(split[1]));
		return map;
	}
	
	public String getLoadAddrByCoords(double lat, double lon) {
		RestRequestManager requestManager = new RestRequestManager();
		requestManager.init();
		
		//키 설정
		requestManager.addHeader(NAVER_MAP_ID_PARAM, naverMapId);
		requestManager.addHeader(NAVER_MAP_SECRET_PARAM, naverMapSecret);
		
		//파라미터 설정
		requestManager.addParam("coords", lon+","+lat);
		requestManager.addParam("output", "json");
		requestManager.addParam("orders", "roadaddr,admcode");
		
		
		ResponseEntity<String> entity = requestManager.request(reverseGeocoding, HttpMethod.GET);
		//logger.info("entity : " + entity);
		
		ObjectMapper mapper = new ObjectMapper();
		//HashMap<String, Object> map = null;
		JsonNode node = null;
		try {
			node = mapper.readTree(entity.getBody());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String addr = null;
		JsonNode result = node.get("results");
		
		JsonNode range = result.get(0);
		
		if(range.get("name").asText().equals("roadaddr")) {
			addr = range.get("land").get("addition0").asText();
		}else {
			addr = range.get("region").get("area3").get("name").asText();
		}
		
		
		return addr;
	}
	public String getLoadAddrByCoords(String latlon) {
		HashMap<String, Double> coords = parseCoords(latlon);
		return this.getLoadAddrByCoords(coords.get("lat"), coords.get("lon"));
	}
	
	public HashMap searchPlaceByLoadAddr(String loadAddr, String place) {

		RestRequestManager requestManager = new RestRequestManager();
		requestManager.init();
		
		requestManager.addHeader(NAVER_SEARCH_ID_PARAM, naverSearchId);
		requestManager.addHeader(NAVER_SEARCH_SECRET_PARAM, naverSearchSecret);
		
		requestManager.addParam("query", loadAddr+" "+place);
		requestManager.addParam("display", "5");
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = null;
		try {
			map = mapper.readValue( requestManager.request(search, HttpMethod.GET).getBody(), HashMap.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
