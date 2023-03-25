package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrToday;
import com.edu.bodybuddy.exception.ExrTodayException;

@Repository
public class MybatisExrTodayDAO implements ExrTodayDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("ExrToday.selectAll");
	}

	
	@Override
	public ExrToday select(int exr_today_idx) {
		return sqlSessionTemplate.selectOne("ExrToday.select", exr_today_idx);
	}

	
	@Override
	public void insert(ExrToday exrToday) throws ExrTodayException{
		int result=sqlSessionTemplate.insert("ExrToday.insert", exrToday);
		if(result<1) {
			throw new ExrTodayException("팁 등록 실패");
		}
	}


	@Override
	public void update(ExrToday exrToday) throws ExrTodayException{
		int result=sqlSessionTemplate.update("ExrToday.update", exrToday);
		
		if(result<1) {
			throw new ExrTodayException("팁 수정 실패");
		}
	}

	@Override
	public void delete(int exr_today_idx) throws ExrTodayException{
		int result=sqlSessionTemplate.delete("ExrToday.delete", exr_today_idx);
		if(result<1) {
			throw new ExrTodayException("팁 삭제 실패");
		}
	}

	@Override
	public void plusHit(int exr_today_idx) throws ExrTodayException{
		int result=sqlSessionTemplate.update("ExrToday.plusHit", exr_today_idx);
		if(result<1) {
			throw new ExrTodayException("조회수 추가 실패");
		}		
	}


	@Override
	public void plusRecommend(int exr_today_idx) throws ExrTodayException{
		int result=sqlSessionTemplate.update("ExrToday.plusRecommend", exr_today_idx);
		if(result<1) {
			throw new ExrTodayException("추천수 추가 실패");
		}			
	}

}
