package com.edu.bodybuddy.model.myrecord;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.myrecord.DailyWalk;
import com.edu.bodybuddy.exception.DailyWalkException;

@Repository
public class MybatisDailyWalkDAO implements DailyWalkDAO{
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override //일단 일간으로 보여주는 식으로 하고, 나중에 월간, 연간으로 확장가능하도록 Map방식으로 감
	public List selectAllWalkRecordForDay() {
		List WalkRecordListForDay=sqlSessionTemplate.selectList("DailyWalk.selectAllWalkRecordForDay");
		return WalkRecordListForDay;
	}
	
	@Override //주간, 월간으로 보여주는 러닝기록 랭킹 메서드
	public List selectAllWalkRecordForWeek() {
		List WalkRecordListForWeek=sqlSessionTemplate.selectList("DailyWalk.selectAllWalkRecordForWeek");
		return WalkRecordListForWeek;
	}
	
	@Override
	public List selectAllWalkRecordForMonth() {
		List WalkRecordListForMonth=sqlSessionTemplate.selectList("DailyWalk.selectAllWalkRecordForMonth");
		return WalkRecordListForMonth;
	}

	@Override //사용자의 해당일에 뛴거리를 입력하는 메서드
	public void insert(DailyWalk dailyWalk) throws DailyWalkException{
		int result=sqlSessionTemplate.insert("DailyWalkd.insert", dailyWalk);
		if(result<1) throw new DailyWalkException("러닝기록 등록 실패");
	}

	@Override //사용자의 해당일에 뛴거리를 삭제하는 메서드
	public void delete(int member_idx) throws DailyWalkException{
		int result=sqlSessionTemplate.delete("DailyWalk.delete", member_idx);
		if(result<1)throw new DailyWalkException("러닝기록 삭제 실패");
	}


	

	

}
