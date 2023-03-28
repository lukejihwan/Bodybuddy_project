package com.edu.bodybuddy.util;

import java.util.HashMap;

import lombok.Data;
@Data
public class MypageManager {
	private int totalRecord; //총 레코드 수
	private int pageSize; //한 페이지당 아이템 수
	private int totalPage; // 총 페이지 수
	private int blockSize; //한 블럭당 페이지 수
	private int currentPage; //현재 페이지
	private int firstPage; //블럭당 시작 페이지
	private int lastPage; //블럭당 마지막 페이지
	private int forNum; //vue의 반복문에서 쓰일 페이지네이션 반복횟수
	private int no; //각 게시물에 할당될 번호(idx를 안 쓰는 경우)
	private boolean hasPrev; //이전버튼 표시 여부
	private boolean hasNext; //다음버튼 표시여부
	
	public MypageManager(int totalRecord, int currentPage, int pageSize, int blocksize) {
		this.totalRecord = totalRecord;
		totalPage = (int)Math.ceil(totalRecord/(float)pageSize);
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.blockSize = blocksize;
		
		firstPage = currentPage - (currentPage-1) % blockSize;
		lastPage = firstPage + (blockSize - 1);
		if(lastPage > totalPage) {
			lastPage = totalPage;
		}
		forNum = lastPage-firstPage+1;
		no = totalRecord-(currentPage-1)*pageSize;
		hasPrev = (firstPage != 1);
		hasNext = (lastPage != totalPage);
	}
		
	
	public HashMap<String, Object> getRange(int page, int pageSize){
		int start = 1+(page-1)*pageSize;
		int end = start + (pageSize-1);
		HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("start", start);
    	map.put("end", end);
    	return map;
	}
	
}
