package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

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

	@Override
	public List selectForMonth(Map<String, String> oneMonthPeiod) throws DietRecordException{
		List<DietRecord> dietRecordListMonth=sqlSessionTemplate.selectList("DietRecord.selectForMonth", oneMonthPeiod);
		if(dietRecordListMonth==null)throw new DietRecordException("해당월에 식단기록 조회 실패");
		return dietRecordListMonth;
	}

	@Override
	public List select(DietRecord dietRecord) throws DietRecordException{
		List dietRecordList=sqlSessionTemplate.selectList("DietRecord.select", dietRecord);
		if(dietRecordList.size()<1) throw new DietRecordException("식단기록 없음");
		return dietRecordList;
	}

	@Override
	public void update(DietRecord dietRecord) throws DietRecordException{
		int result=sqlSessionTemplate.update("DietRecord.update", dietRecord);
		if(result<1) throw new DietRecordException("식단기록 수정 실패");
	}

	@Override
	public void delete(DietRecord dietRecord) throws DietRecordException{
		int result=sqlSessionTemplate.delete("DietRecord.delete", dietRecord);
		if(result<1)throw new DietRecordException("식단기록 삭제 실패");
	}

}
