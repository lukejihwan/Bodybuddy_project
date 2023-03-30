package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrTip;
import com.edu.bodybuddy.exception.ExrRoutineException;
import com.edu.bodybuddy.exception.ExrTipException;

import lombok.Data;

@Repository
public class MybatisExrTipDAO implements ExrTipDAO{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("ExrTip.selectAll");
	}

	
	@Override
	public ExrTip select(int exr_tip_idx) {
		return sqlSessionTemplate.selectOne("ExrTip.select", exr_tip_idx);
	}

	
	@Override
	public void insert(ExrTip exrTip) throws ExrTipException{
		int result=sqlSessionTemplate.insert("ExrTip.insert", exrTip);
		if(result<1) {
			throw new ExrTipException("팁 등록 실패");
		}
	}


	@Override
	public void update(ExrTip exrTip) throws ExrTipException{
		logger.info("디에이오에서 확인 "+exrTip);
		
		int result=sqlSessionTemplate.update("ExrTip.update", exrTip);
		logger.info("업데이트 결과 확인 "+result);
		if(result<1) {
			throw new ExrTipException("팁 수정 실패");
		}
	}

	@Override
	public void delete(int exr_tip_idx) throws ExrTipException{
		int result=sqlSessionTemplate.delete("ExrTip.delete", exr_tip_idx);
		if(result<1) {
			throw new ExrTipException("팁 삭제 실패");
		}
	}

	@Override
	public void plusHit(int exr_tip_idx) throws ExrTipException{
		int result=sqlSessionTemplate.update("ExrTip.plusHit", exr_tip_idx);
		if(result<1) {
			throw new ExrTipException("조회수 추가 실패");
		}		
	}


	@Override
	public void plusRecommend(int exr_tip_idx) throws ExrTipException{
		int result=sqlSessionTemplate.update("ExrTip.plusRecommend", exr_tip_idx);
		if(result<1) {
			throw new ExrTipException("추천수 추가 실패");
		}			
	}

}
