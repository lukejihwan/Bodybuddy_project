package com.edu.bodybuddy.model.diet;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.diet.DietInfo;
import com.edu.bodybuddy.exception.DietInfoException;

@Repository
public class MybatisDietInfoDAO implements DietInfoDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("DietInfo.selectAll");
	}

	@Override
	public DietInfo select(int diet_info_idx) {
		return sqlSessionTemplate.selectOne("DietInfo.select", diet_info_idx);
	}
	
	@Override
	public List selectByIdx(int diet_category_idx) {
		return sqlSessionTemplate.selectList("DietInfo.selectByIdx", diet_category_idx);
	}
	
	@Override
	public List selectBySearch(HashMap<String, String> map) {
		return sqlSessionTemplate.selectList("DietInfo.selectBySearch", map);
	}

	@Override
	public void insert(DietInfo dietInfo) throws DietInfoException{
		int result=sqlSessionTemplate.insert("DietInfo.insert",dietInfo);
		if(result<1) {
			throw new DietInfoException("글 등록 실패");
		}
	}

	@Override
	public void update(DietInfo dietInfo) throws DietInfoException{
		int result=sqlSessionTemplate.update("DietInfo.update",dietInfo);
		if(result<1) {
			throw new DietInfoException("글 수정 실패");
		}
		
	}

	@Override
	public void delete(int diet_info_idx) throws DietInfoException{
		int result=sqlSessionTemplate.delete("DietInfo.delete",diet_info_idx);
		if(result<1) {
			throw new DietInfoException("글 삭제 실패");
		}
	}

}
