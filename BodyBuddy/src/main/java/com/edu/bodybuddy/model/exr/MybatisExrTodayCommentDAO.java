package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrTodayComment;
import com.edu.bodybuddy.exception.ExrTodayCommentException;

@Repository
public class MybatisExrTodayCommentDAO implements ExrTodayCommentDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List selectAllByToday(int exr_today_idx) {
		return sqlSessionTemplate.selectList("ExrTodayComment.selectAllByToday", exr_today_idx);
	}


	@Override
	public void insert(ExrTodayComment exrTodayComment) throws ExrTodayCommentException{
		int result=sqlSessionTemplate.insert("ExrTodayComment.insert", exrTodayComment);
		if(result<1) {
			throw new ExrTodayCommentException("댓글 등록 실패");
		}
	}
	
	
	@Override
	public void update(ExrTodayComment exrTodayComment) throws ExrTodayCommentException{
		int result=sqlSessionTemplate.update("ExrTodayComment.update", exrTodayComment);
		if(result<1) {
			throw new ExrTodayCommentException("댓글 post 수정 실패");
		}
	}

	
	@Override
	public void delete(int exr_today_comment_idx) throws ExrTodayCommentException{
		int result=sqlSessionTemplate.delete("ExrTodayComment.delete", exr_today_comment_idx);
		if(result<1) {
			throw new ExrTodayCommentException("댓글삭제 실패");
		}
	}

	
	@Override
	public int totalCount(int exr_today_idx) {
		return sqlSessionTemplate.selectOne("ExrTodayComment.totalCount", exr_today_idx);
	}
	
	
	@Override
	public int selectMaxStep(ExrTodayComment exrTodayComment) {
		return sqlSessionTemplate.selectOne("ExrTodayComment.selectMaxStep", exrTodayComment);
	}
	
	
	@Override
	public void reply(ExrTodayComment exrTodayComment) throws ExrTodayCommentException{
		int result=sqlSessionTemplate.insert("ExrTodayComment.reply", exrTodayComment);
		if(result<1) {
			throw new ExrTodayCommentException("대댓글 등록 실패");
		}
	}
	

	
	@Override
	public void updateStep(ExrTodayComment exrTodayComment) throws ExrTodayCommentException{
		int result=sqlSessionTemplate.update("ExrRoutineComment.updateStep", exrTodayComment);
		if(result<1) {
			throw new ExrTodayCommentException("대댓글 등록 단계 실패");
		}
	}


}
