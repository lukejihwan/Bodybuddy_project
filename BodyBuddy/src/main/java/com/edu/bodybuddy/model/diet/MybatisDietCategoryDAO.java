package com.edu.bodybuddy.model.diet;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.exception.DietCategoryException;

@Repository
public class MybatisDietCategoryDAO implements DietCategoryDAO{

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		List list=sqlSessionTemplate.selectList("DietCategory.selectAll");
		return list;
	}

	@Override
	public DietCategory select(int diet_category_idx) {
		return sqlSessionTemplate.selectOne("DietCategory.select", diet_category_idx);
	}

	@Override
	public void insert(DietCategory diet_Category) throws DietCategoryException{
		int result=sqlSessionTemplate.insert("DietCategory.insert", diet_Category);
		if(result<1) {
			throw new DietCategoryException("카테고리 등록 실패");
		}
		
	}

	@Override
	public void update(DietCategory diet_Category) throws DietCategoryException{
		int result=sqlSessionTemplate.update("DietCategory.update", diet_Category);
		if(result<1) {
			throw new DietCategoryException("카테고리 수정 실패");
		}
	}

	@Override
	public void delete(int diet_category_idx) throws DietCategoryException{
		int result=sqlSessionTemplate.delete("DietCategory.delete", diet_category_idx);
		if(result<1) {
			throw new DietCategoryException("카테고리 삭제 실패");
		}	
	}
}
