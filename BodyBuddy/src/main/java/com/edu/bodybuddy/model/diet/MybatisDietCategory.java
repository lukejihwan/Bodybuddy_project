package com.edu.bodybuddy.model.diet;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.exception.Diet_CategoryException;

@Repository
public class MybatisDietCategory implements DietCategoryDAO{

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		List list=sqlSessionTemplate.selectList("Diet_Category.selectAll");
		return list;
	}

	@Override
	public DietCategory select(int diet_category_idx) {
		return sqlSessionTemplate.selectOne("Diet_Category.select", diet_category_idx);
	}

	@Override
	public void insert(DietCategory diet_Category) throws Diet_CategoryException{
		int result=sqlSessionTemplate.insert("Diet_Category.insert", diet_Category);
		if(result<1) {
			throw new Diet_CategoryException("카테고리 등록 실패");
		}
		
	}

	@Override
	public void update(DietCategory diet_Category) throws Diet_CategoryException{
		int result=sqlSessionTemplate.update("Diet_Category.update", diet_Category);
		if(result<1) {
			throw new Diet_CategoryException("카테고리 수정 실패");
		}
	}

	@Override
	public void delete(int diet_category_idx) throws Diet_CategoryException{
		int result=sqlSessionTemplate.delete("Diet_Category.delete", diet_category_idx);
		if(result<1) {
			throw new Diet_CategoryException("카테고리 삭제 실패");
		}	
	}
}
