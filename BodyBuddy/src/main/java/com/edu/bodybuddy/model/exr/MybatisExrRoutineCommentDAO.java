package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrRoutineComment;
import com.edu.bodybuddy.exception.ExrRoutineCommentException;
@Repository
public class MybatisExrRoutineCommentDAO implements ExrRoutineCommentDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List selectByFk(int exr_routine_idx) {
		return sqlSessionTemplate.selectList("ExrRoutineComment.selectByFk", exr_routine_idx);
	}

	@Override
	public void insert(ExrRoutineComment exrRoutineComment) throws ExrRoutineCommentException{
		int result=sqlSessionTemplate.insert("ExrRoutineComment.insert", exrRoutineComment);
		if(result<1) {
			throw new ExrRoutineCommentException("댓글 등록 실패");
		}
	}

	@Override
	public void delete(int exr_routine_comment_idx) throws ExrRoutineCommentException{
		int result=sqlSessionTemplate.delete("ExrRoutineComment.delete", exr_routine_comment_idx);
		if(result<1) {
			throw new ExrRoutineCommentException("댓글삭제 실패");
		}
	}

	@Override
	public void reply(ExrRoutineComment exrRoutineComment) throws ExrRoutineCommentException{
		int result=sqlSessionTemplate.insert("ExrRoutineComment.reply", exrRoutineComment);
		if(result<1) {
			throw new ExrRoutineCommentException("댓글 등록 실패");
		}
	}

	@Override
	public void updateStep(ExrRoutineComment exrRoutineComment) throws ExrRoutineCommentException{
		int result=sqlSessionTemplate.update("ExrRoutineComment.updateStep", exrRoutineComment);
		if(result<1) {
			throw new ExrRoutineCommentException("댓글 등록 단계 실패");
		}
	}
	


}
