package com.edu.bodybuddy.model.myrecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.myrecord.ExrRecord;
import com.edu.bodybuddy.exception.ExrRecordException;

@Repository
public class MybatisExrRecordDAO implements ExrRecordDAO {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//한달간의 기록을 가져오는 메서드
	@Override
	public List<ExrRecord> seletForMonth(Map<String,String> oneMonthPeriod) throws ExrRecordException{
		List<ExrRecord> exrRecordListMonth=sqlSessionTemplate.selectList("ExrRecord.selectForMonth", oneMonthPeriod);
		if(exrRecordListMonth.size()<1) {
			throw new ExrRecordException("해당월 운동기록 조회 실패");
		}
		return exrRecordListMonth;
	}
	
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
	public void update(ExrRecord exrRecord) throws ExrRecordException{
		int result=sqlSessionTemplate.update("ExrRecord.update", exrRecord);
		if(result<1) {
			throw new ExrRecordException("운동기록 수정 실패");
		}
	}

	@Override
	public void delete(int exr_record_idx) throws ExrRecordException{
		int result=sqlSessionTemplate.delete("ExrRecord.delete", exr_record_idx);
		if(result<1) {
			throw new ExrRecordException("운동기록 삭제 실패");
		}
	}

	@Override
	public List<ExrRecord> selectForDay(HashMap map) throws ExrRecordException{
		logger.info("하루동안의 기록을 조회하기 위해 받아온 regdate :"+map.get("regdate"));
		logger.info("하루동안의 기록을 조회하기 위해 받아온 member_idx :"+map.get("member_idx"));
		List<ExrRecord> exrList=sqlSessionTemplate.selectList("ExrRecord.selectForDay", map);
		logger.info("ExrRecordMapper에서 반환받은 운동의 갯수는 :"+exrList.size());
		if(exrList.size()<1) {
			throw new ExrRecordException("해당일 운동기록 조회 실패");
		}
		return exrList;
	}

	

	

}
