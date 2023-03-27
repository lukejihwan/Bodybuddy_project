package com.edu.bodybuddy.model.myrecord;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.myrecord.DietRecord;
import com.edu.bodybuddy.exception.DietRecordException;

@Repository
public class MybatisDietRecordDAO implements DietRecordDAO{

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(DietRecord dietRecord) throws DietRecordException{
		int result=sqlSessionTemplate.insert("DietRecord.insert", dietRecord);
		if(result<1)throw new DietRecordException("식단기록 등록 실패");
	}

}
