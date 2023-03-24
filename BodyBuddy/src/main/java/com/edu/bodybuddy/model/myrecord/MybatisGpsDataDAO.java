package com.edu.bodybuddy.model.myrecord;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrTip;
import com.edu.bodybuddy.domain.myrecord.GpsData;
import com.edu.bodybuddy.exception.ExrTipException;
import com.edu.bodybuddy.exception.GpsDataException;

@Repository
public class MybatisGpsDataDAO implements GpsDataDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectForDay(String regdate) {
		return sqlSessionTemplate.selectList("GpsData.selectForDay", regdate);
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

	
}
