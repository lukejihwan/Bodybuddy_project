package com.edu.bodybuddy.model.diet;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.diet.DietShareComments;
import com.edu.bodybuddy.domain.diet.DietTipComments;
import com.edu.bodybuddy.exception.DietShareCommentsException;
import com.edu.bodybuddy.exception.DietTipCommentsException;

@Repository
public class MyBatisDietShareComments implements DietShareCommentsDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate; 
	
	
	@Override
	public List selectByIdx(int diet_share_idx) {
		return sqlSessionTemplate.selectList("DietShareComments.selectByIdx", diet_share_idx);
	}

	@Override
	public void insert(DietShareComments dietShareComments) throws DietShareCommentsException{
		int result=sqlSessionTemplate.insert("DietShareComments.insert", dietShareComments);
		if(result<1) {
			throw new DietShareCommentsException("댓글 등록 실패");
		}
	}

	@Override
	public void update(DietShareComments dietShareComments) throws DietShareCommentsException{
		int result=sqlSessionTemplate.update("DietShareComments.update", dietShareComments);
		if(result<1) {
			throw new DietShareCommentsException("댓글 수정 실패");
		}
	}

	@Override
	public void delete(int diet_share_comments_idx) throws DietShareCommentsException{
		int result=sqlSessionTemplate.delete("DietShareComments.delete", diet_share_comments_idx);
		if(result<1) {
			throw new DietShareCommentsException("댓글 삭제 실패");
		}
	}

	@Override
	public void updateByPost(DietShareComments dietShareComments) throws DietShareCommentsException{
		int result=sqlSessionTemplate.update("DietShareComments.updateByPost", dietShareComments);
		if(result<1) {
			throw new DietShareCommentsException("Post 수정 실패");
		}
	}

	@Override
	public int totalCount(int diet_share_idx) {
		return sqlSessionTemplate.selectOne("DietTipComments.totalCount", diet_share_idx);
	}

	@Override
	public int selectMaxStep(DietShareComments dietShareComments) {
		return sqlSessionTemplate.selectOne("DietShareComments.selectMaxStep", dietShareComments);
	}

}
