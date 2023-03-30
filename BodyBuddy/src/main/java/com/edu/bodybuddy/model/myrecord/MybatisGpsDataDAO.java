package com.edu.bodybuddy.model.myrecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.myrecord.GpsData;
import com.edu.bodybuddy.exception.GpsDataException;

@Repository
public class MybatisGpsDataDAO implements GpsDataDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectForDay(HashMap map) {
		return sqlSessionTemplate.selectList("GpsData.selectForDay", map);
	}

	
	@Override
	public void insert(GpsData gpsData) throws GpsDataException{
		int result=sqlSessionTemplate.insert("GpsData.insert", gpsData);
		if(result<1) {
			throw new GpsDataException("위치 데이터 등록 실패");
		}
	}
	
	
	@Override
	public void delete(int exr_record_idx) throws GpsDataException{
		int result=sqlSessionTemplate.delete("GpsData.delete", exr_record_idx);
		if(result<1) {
			throw new GpsDataException("팁 삭제 실패");
		}
	}

	/*지환 영역 시작*/
	@Override
	public List<GpsData> selectForMonth(Map<String, String> runningOneMonthPeriod) throws GpsDataException{
		//Set배열 사용가능한지 확인 필요
		List<GpsData> runningListForMonth=sqlSessionTemplate.selectList("GpsData.selectForMonth", runningOneMonthPeriod);
		if(runningListForMonth==null)throw new GpsDataException("러닝기록 조회 결과 없음");
		return runningListForMonth;
	}
	/*지환 영역 끝*/
	
}
