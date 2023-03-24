package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrTip;
import com.edu.bodybuddy.exception.ExrTipException;

import lombok.Data;

@Repository
public class MybatisExrTipDAO implements ExrTipDAO{
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
		int result=sqlSessionTemplate.update("ExrTip.update", exrTip);
		
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

}
