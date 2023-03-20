package com.edu.bodybuddy.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edu.bodybuddy.exception.UploadException;

@Component
public class FileManager {
	//파일명 생성하기
	public String createFilename(String filename) {
		//현재시간을 기반으로 파일명 생성
		long time = System.currentTimeMillis();
		
		
		//확장자 추출(. 포함)
		String ext = filename.substring(filename.lastIndexOf("."), filename.length());
		return time+ext;
	}
	
	//지정된 디렉토리로 파일을 저장
	public List save(MultipartFile[] photoList, String dir) throws UploadException{ //dir = 저장될 디렉토리
		List<String> filenameList = new ArrayList();
		try {
			for(MultipartFile photo : photoList) {
				
				String newFilename = createFilename(photo.getOriginalFilename());
				photo.transferTo(new File(dir+newFilename));
				
				filenameList.add(newFilename);
				Thread.sleep(10);
			}
		} catch (IllegalStateException e) {
			//예외의 전환
			throw new UploadException("파일 업로드 실패", e);
		} catch (IOException e) {
			throw new UploadException("파일 업로드 실패", e);
		} catch (InterruptedException e) {
			throw new UploadException("파일 업로드 실패", e);
		}
		return filenameList;
	}
}
