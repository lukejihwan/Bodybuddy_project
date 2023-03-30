package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.exception.ExrNoticeException;

@Repository
public class MybatisExrNoticeDAO implements ExrNoticeDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("ExrNotice.selectAll");
	}

	
	@Override
	public ExrNotice select(int exr_notice_idx) {
		return sqlSessionTemplate.selectOne("ExrNotice.select", exr_notice_idx);
	}

	
	@Override
	public ExrNotice selectByCategory(int exr_category_idx) {
		logger.info("카테고리 수는?"+exr_category_idx);
		ExrNotice exrNotice=sqlSessionTemplate.selectOne("ExrNotice.selectByCategory", exr_category_idx);
		logger.info("디에이오에서 결과 확인"+exrNotice);
		
		return exrNotice;
	}
	
	
	
	@Override
	public void insert(ExrNotice exrNotice) throws ExrNoticeException{
		int result=sqlSessionTemplate.insert("ExrNotice.insert", exrNotice);
		if(result<1) {
			throw new ExrNoticeException("운동정보글 입력 실패");
		}
	}

	
	@Override
	public void update(ExrNotice exrNotice) throws ExrNoticeException{
		logger.info("디에이오에서 확인 "+exrNotice);
		
		int result=sqlSessionTemplate.update("ExrNotice.update", exrNotice);
		if(result<1) {
			throw new ExrNoticeException("운동정보글 수정 실패");
		}
	}


	@Override
	public void delete(int exr_notice_idx) throws ExrNoticeException{
		int result=sqlSessionTemplate.delete("ExrNotice.delete", exr_notice_idx);
		if(result<1) {
			throw new ExrNoticeException("운동정보글 삭제 실패");
		}
	}


	

}
