package com.edu.bodybuddy.model.myrecord;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;
import com.edu.bodybuddy.exception.ExrRecordException;

@Repository
public class MybatisExrRecordDAO implements ExrRecordDAO {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(ExrRecord exrRecord) throws ExrRecordException{
		logger.info("처음 exrRecord_idx 값은 : "+exrRecord.getExr_record_idx());
		int result=sqlSessionTemplate.insert("ExrRecord.insert", exrRecord);
		logger.info("후의 exrRecord_idx 값은 : "+exrRecord.getExr_record_idx());
		if(result<1) {
			throw new ExrRecordException("운동기록 등록 실패");
		}
	}

	@Override
	public void delete(ExrRecord exrRecord) throws ExrRecordException{
		int result=sqlSessionTemplate.delete("ExrRecord.delete", exrRecord);
		if(result<1) {
			throw new ExrRecordException("운동기록 삭제 실패");
		}
	}

}
