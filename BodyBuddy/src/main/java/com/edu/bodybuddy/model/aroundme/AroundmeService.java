package com.edu.bodybuddy.model.aroundme;

import java.util.HashMap;
import java.util.Map;

public interface AroundmeService {
	public HashMap<String, Object> getPlaceByCoords(String place, String latlon);
	public HashMap<String, Object> getPlaceByAddr(String place, String addr);
	public HashMap<String, Object> getCoordsByAddr(String addr);
	public HashMap<String, Object> getBlogByTitle(String title, int page);
	public String getAddrByCoords(String latlon);
}
