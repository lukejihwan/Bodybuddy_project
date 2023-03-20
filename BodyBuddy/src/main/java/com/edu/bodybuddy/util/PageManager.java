package com.edu.bodybuddy.util;

import lombok.Data;

@Data
public class PageManager {
	private int totalRecord; //총 레코드 수
	private int pageSize=10; //한 페이지당 아이템 수
	private int totalPage; // 총 페이지 수
	private int blockSize = 10; //한 블럭당 페이지 수
	private int currentPage = 1; //현재 페이지
	private int firstPage; //블럭당 시작 페이지
	private int lastPage; //블럭당 마지막 페이지
	private int curPos; //페이지당 ArrayList의 시작 index
	private int num; //페이지당 시작 번호
	
	public void init(int tr, int cp) {
		totalRecord = tr;
		totalPage = (int)Math.ceil(totalRecord/(double)pageSize);
		
		if(cp<1) currentPage = 1;
		else currentPage = cp;
		
		firstPage = currentPage - (currentPage-1) % blockSize;
		lastPage = firstPage + (blockSize - 1);
		curPos = (currentPage-1) * pageSize;
		num = totalRecord - curPos;
	}
}
