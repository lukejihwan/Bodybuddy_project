package com.edu.bodybuddy.model.exr;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edu.bodybuddy.domain.exr.ExrToday;
import com.edu.bodybuddy.exception.ExrTodayException;

@Service
public class ExrTodayServiceImpl implements ExrTodayService{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrTodayDAO exrTodayDAO;
	
	@Override
	public List selectAll() {
		return exrTodayDAO.selectAll();
	}

	@Override
	public ExrToday select(int exr_today_idx) {
		return exrTodayDAO.select(exr_today_idx);
	}

	
	// 안드로이드에게 받은 파일 객체 가공하기
	@Override
	public void insert(ExrToday exrToday) throws ExrTodayException{
		
		/*
		 * // 날라온 데이터 값 가공하기 MultipartFile file=exrToday.getFile();
		 * 
		 * // 굳이 버퍼로 해야할까? String encoded=null;
		 * 
		 * try { byte[] bytes=file.getBytes();
		 * encoded=Base64.getEncoder().encodeToString(bytes);
		 * 
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 * 
		 * String content="<img src='"+encoded+"'>";
		 * System.out.println("들어갈 내용 "+content);
		 * 
		 * exrToday.setContent(content);
		 * System.out.println("서비스에서 인서트하기 전 확인"+exrToday);
		 */
		
		exrTodayDAO.insert(exrToday);
	}

	@Override
	public void update(ExrToday exrToday) throws ExrTodayException{
		exrTodayDAO.update(exrToday);
	}

	@Override
	public void delete(int exr_today_idx) throws ExrTodayException{
		exrTodayDAO.delete(exr_today_idx);
	}

	@Override
	public void plusHit(int exr_today_idx) throws ExrTodayException{
		exrTodayDAO.plusHit(exr_today_idx);
	}

	@Override
	public void plusRecommend(int exr_today_idx) throws ExrTodayException{
		exrTodayDAO.plusRecommend(exr_today_idx);
	}


}
