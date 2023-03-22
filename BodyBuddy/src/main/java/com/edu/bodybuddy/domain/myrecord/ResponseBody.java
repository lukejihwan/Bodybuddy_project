package com.edu.bodybuddy.domain.myrecord;

import java.util.List;

import lombok.Data;

@Data
public class ResponseBody {
	private String dataType;
	private Items items;
	private int pageNo;
	private int numOfRows;
	private int totalCount;
}
