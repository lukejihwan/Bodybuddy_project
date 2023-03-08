package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.exception.ExrCategoryException;

@Repository
public class MybatisExrCategory implements ExrCategoryDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("ExrCategory.selectAll");
	}


	@Override
	public ExrCategory select(int exr_category_idx) {
		return sqlSessionTemplate.selectOne("ExrCategory.select", exr_category_idx);
	}


	@Override
	public void insert(ExrCategory exr_category) throws ExrCategoryException{
		int result=sqlSessionTemplate.insert("ExrCategory.insert", exr_category);
		if(result<1) {
			throw new ExrCategoryException("카테고리 입력 실패");
		}
	}

	@Override
	public void update(ExrCategory exr_category) {
		int result=sqlSessionTemplate.update("ExrCategory.update", exr_category);
		if(result<1) {
			throw new ExrCategoryException("카테고리 수정 실패");
		}
	}


	@Override
	public void delete(int exr_category_idx) {
		int result=sqlSessionTemplate.delete("ExrCategory.delete", exr_category_idx);
		if(result<1) {
			throw new ExrCategoryException("카테고리 삭제 실패");
		}
	}

	
}
