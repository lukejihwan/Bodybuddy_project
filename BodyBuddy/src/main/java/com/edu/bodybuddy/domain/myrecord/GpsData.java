package com.edu.bodybuddy.domain.myrecord;


import lombok.Data;

// gps 데이터를 받을 객체
@Data
public class GpsData {
	private int gpsdata_idx;
	private int member_idx;
	private double lati;
	private double longi;
	private String regdate;

}
