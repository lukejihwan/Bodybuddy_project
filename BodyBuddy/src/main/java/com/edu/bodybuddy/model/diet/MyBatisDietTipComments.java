package com.edu.bodybuddy.model.diet;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.diet.DietTipComments;
import com.edu.bodybuddy.exception.DietTipCommentsException;

@Repository
public class MyBatisDietTipComments implements DietTipCommentsDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate; 
	
	
	@Override
	public List selectByIdx(int diet_tip_idx) {
		return sqlSessionTemplate.selectList("DietTipComments.selectByIdx", diet_tip_idx);
	}

	@Override
	public void insert(DietTipComments dietTipComments) throws DietTipCommentsException{
		int result=sqlSessionTemplate.insert("DietTipComments.insert", dietTipComments);
		if(result<1) {
			throw new DietTipCommentsException("댓글 등록 실패");
		}
	}

	@Override
	public void update(DietTipComments dietTipComments) throws DietTipCommentsException{
		int result=sqlSessionTemplate.update("DietTipComments.update", dietTipComments);
		if(result<1) {
			throw new DietTipCommentsException("댓글 수정 실패");
		}
	}

	@Override
	public void delete(int diet_tip_comments_idx) throws DietTipCommentsException{
		int result=sqlSessionTemplate.delete("DietTipComments.delete", diet_tip_comments_idx);
		if(result<1) {
			throw new DietTipCommentsException("댓글 삭제 실패");
		}
	}

	@Override
	public void updateByPost(DietTipComments dietTipComments) throws DietTipCommentsException{
		int result=sqlSessionTemplate.update("DietTipComments.updateByPost", dietTipComments);
		if(result<1) {
			throw new DietTipCommentsException("Post 수정 실패");
		}
	}

	@Override
	public int totalCount(int diet_tip_idx) {
		return sqlSessionTemplate.selectOne("DietTipComments.totalCount", diet_tip_idx);
	}

	@Override
	public int selectMaxStep(DietTipComments dietTipComments) {
		return sqlSessionTemplate.selectOne("DietTipComments.selectMaxStep", dietTipComments);
	}

}
