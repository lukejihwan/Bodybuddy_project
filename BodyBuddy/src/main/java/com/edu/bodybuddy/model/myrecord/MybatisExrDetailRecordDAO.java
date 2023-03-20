package com.edu.bodybuddy.model.myrecord;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.myrecord.ExrDetailRecord;
import com.edu.bodybuddy.exception.ExrDetailRecordException;
@Repository
public class MybatisExrDetailRecordDAO implements ExrDetailRecordDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(ExrDetailRecord exrDetailRecord) throws ExrDetailRecordException {
		int result=sqlSessionTemplate.insert("ExrDetailRecord.insertByExrRecord", exrDetailRecord);
		if(result<1) {
			throw new ExrDetailRecordException("운동상세정보 등록 실패");
		}
	}

	@Override
	public void delete(ExrDetailRecord exrDetailRecord) throws ExrDetailRecordException{
		int result=sqlSessionTemplate.delete("ExrDetailRecord.deleteByExrRecord", exrDetailRecord);
		if(result<1) {
			throw new ExrDetailRecordException("운동상세정보 삭제 실패");
		}
	}

}
