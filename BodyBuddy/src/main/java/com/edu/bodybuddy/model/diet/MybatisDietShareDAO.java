package com.edu.bodybuddy.model.diet;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.diet.DietShare;
import com.edu.bodybuddy.exception.DietShareException;

@Repository
public class MybatisDietShareDAO implements DietShareDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("DietShare.selectAll");
	}

	@Override
	public DietShare select(int diet_share_idx) {
		return sqlSessionTemplate.selectOne("DietShare.select", diet_share_idx);
	}

	@Override
	public List selectByIdx(int diet_category_idx) {
		return sqlSessionTemplate.selectList("DietShare.selectByIdx", diet_category_idx);
	}

	@Override
	public void insert(DietShare dietShare) throws DietShareException{
		int result=sqlSessionTemplate.insert("DietShare.insert", dietShare);
		if(result<1) {
			throw new DietShareException("글 등록 실패");
		}	
	}

	@Override
	public void update(DietShare dietShare) throws DietShareException{
		int result=sqlSessionTemplate.update("DietShare.update", dietShare);
		if(result<1) {
			throw new DietShareException("글 수정 실패");
		}
	}

	@Override
	public void delete(int diet_share_idx) throws DietShareException{
		int result=sqlSessionTemplate.delete("DietShare.delete", diet_share_idx);
		if(result<1) {
			throw new DietShareException("글 삭제 실패");
		}
	}

	@Override
	public void addHit(int diet_share_idx) throws DietShareException{
		int result=sqlSessionTemplate.update("DietShare.addHit", diet_share_idx);
		if(result<1) {
			throw new DietShareException("조회수 추가 실패");
		}
	}

	@Override
	public void addRecommend(int diet_share_idx) throws DietShareException{
		int result=sqlSessionTemplate.update("DietShare.addRecommend", diet_share_idx);
		if(result<1) {
			throw new DietShareException("찜하기 등록 실패");
		}
	}

	@Override
	public List selectBySearch(HashMap<String, String> map) {
		return sqlSessionTemplate.selectList("DietShare.selectBySearch", map);
	}

}
