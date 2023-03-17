package com.edu.bodybuddy.model.exr;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrRoutine;
import com.edu.bodybuddy.exception.ExrRoutineException;

@Repository
public class MybatisExrRoutineDAO implements ExrRoutineDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("ExrRoutine.selectAll");
	}
	
	@Override
	public ExrRoutine select(int exr_routine_idx) {
		return sqlSessionTemplate.selectOne("ExrRoutine.select", exr_routine_idx);
	}
	
	// 검색 기능
	@Override
	public List selectBySearch(HashMap<String, String> map) {
		return sqlSessionTemplate.selectList("ExrRoutine.selectBySearch", map);
	}
	
	// 총 레코드 개수를 반환하는 함수
	@Override
	public int totalCount() {
		return sqlSessionTemplate.selectOne("ExrRoutine.totalCount");
	}

	// 페이징 처리를 위한 함수
	@Override
	public List selectAllByPage(int pg) {
		return sqlSessionTemplate.selectList("ExrRoutine.selectAllByPage", pg);
	}

	
	@Override
	public void insert(ExrRoutine exrRoutine) throws ExrRoutineException{
		int result=sqlSessionTemplate.insert("ExrRoutine.insert", exrRoutine);
		if(result<1) {
			throw new ExrRoutineException("루틴 공유 글 등록 실패");
		}
	}

	@Override
	public void update(ExrRoutine exrRoutine) {
		System.out.println("루틴의 카테고리는? "+exrRoutine.getExrCategory());
		System.out.println("디에이오에서 확인 "+exrRoutine);
		int result=sqlSessionTemplate.update("ExrRoutine.update", exrRoutine);
		System.out.println("디에이오에서 확인 "+exrRoutine);
		
		if(result<1) {
			throw new ExrRoutineException("루틴 공유 글 수정 실패");
		}
	}

	@Override
	public void delete(int exr_routine_idx) {
		int result=sqlSessionTemplate.delete("ExrRoutine.delete", exr_routine_idx);
		if(result<1) {
			throw new ExrRoutineException("루틴 공유 글 삭제 실패");
		}
	}





}
