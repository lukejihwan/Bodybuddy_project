package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.Exr_Category;
import com.edu.bodybuddy.exception.Exr_CategoryException;

@Repository
public class MybatisExr_Category implements Exr_CategoryDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Exr_Category.selectAll");
	}


	@Override
	public Exr_Category select(int exr_Category_idx) {
		return sqlSessionTemplate.selectOne("Exr_Category.select", exr_Category_idx);
	}


	@Override
	public void insert(Exr_Category exr_Category) throws Exr_CategoryException{
		int result=sqlSessionTemplate.insert("Exr_Category.insert", exr_Category);
		if(result<1) {
			throw new Exr_CategoryException("카테고리 입력 실패");
		}
	}

	@Override
	public void update(Exr_Category exr_Category) {
		int result=sqlSessionTemplate.update("Exr_Category.update", exr_Category);
		if(result<1) {
			throw new Exr_CategoryException("카테고리 수정 실패");
		}
	}


	@Override
	public void delete(int exr_Category_idx) {
		int result=sqlSessionTemplate.delete("Exr_Category.delete", exr_Category_idx);
		if(result<1) {
			throw new Exr_CategoryException("카테고리 삭제 실패");
		}
	}

	
}
