package com.edu.bodybuddy.model.exr;

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
	public ExrRoutine select(int exr_routine_idx) {
		return sqlSessionTemplate.selectOne("ExrRoutine.select", exr_routine_idx);
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
		int result=sqlSessionTemplate.update("ExrRoutine.update", exrRoutine);
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
