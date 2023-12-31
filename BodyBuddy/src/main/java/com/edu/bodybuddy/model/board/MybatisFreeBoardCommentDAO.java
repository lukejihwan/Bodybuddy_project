package com.edu.bodybuddy.model.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.board.FreeBoardComment;
import com.edu.bodybuddy.exception.FreeBoardCommentException;

@Repository
public class MybatisFreeBoardCommentDAO implements BoardCommentDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAllByBoard(int free_board_idx) {
		return sqlSessionTemplate.selectList("FreeBoardComment.selectAllByBoard", free_board_idx);
	}

	public List selectAllByMember(int member_idx) {
		return sqlSessionTemplate.selectList("FreeBoardComment.selectAllByMember", member_idx);
	}

	public void insert(Object freeBoardComment) throws FreeBoardCommentException{
		int result = sqlSessionTemplate.insert("FreeBoardComment.insert", freeBoardComment); 
		if(result < 1) throw new FreeBoardCommentException("자유게시판 댓글 등록 실패");
	}

	public void update(Object freeBoardComment) throws FreeBoardCommentException{
		int result = sqlSessionTemplate.update("FreeBoardComment.update", freeBoardComment); 
		if(result < 1) throw new FreeBoardCommentException("자유게시판 댓글 수정 실패");
	}

	public void delete(int free_board_comment_idx) throws FreeBoardCommentException{
		int result = sqlSessionTemplate.delete("FreeBoardComment.delete", free_board_comment_idx); 
		if(result < 1) throw new FreeBoardCommentException("자유게시판 댓글 삭제 실패");
	}

	public int totalCount(int free_board_idx) {
		return sqlSessionTemplate.selectOne("FreeBoardComment.totalCount", free_board_idx);
	}

	public int maxStep(int free_board_idx) {
		return sqlSessionTemplate.selectOne("FreeBoardComment.maxStep", free_board_idx);
	}

	public void shiftAboveSteps(Object object) throws FreeBoardCommentException{
		FreeBoardComment freeBoardComment = (FreeBoardComment)object; 
		int countAboveSteps = sqlSessionTemplate.selectOne("FreeBoardComment.countAboveSteps", freeBoardComment);
		int result = sqlSessionTemplate.update("FreeBoardComment.shiftAboveSteps", freeBoardComment);
		if(
				countAboveSteps < result ||
				countAboveSteps > result) {
			throw new FreeBoardCommentException("대댓글 삽입을 위해 step 올리기 실패");
		}
	}
	public void unshiftAboveSteps(Object object) throws FreeBoardCommentException{
		FreeBoardComment freeBoardComment = (FreeBoardComment)object; 
		int countAboveSteps = sqlSessionTemplate.selectOne("FreeBoardComment.countAboveSteps", freeBoardComment);
		int result = sqlSessionTemplate.update("FreeBoardComment.unshiftAboveSteps", freeBoardComment);
		if(
				countAboveSteps < result ||
				countAboveSteps > result) {
			throw new FreeBoardCommentException("대댓글 삽입을 위해 step 올리기 실패");
		}
	}

	public int maxStepInChild(Object freeBoardComment) {
		return sqlSessionTemplate.selectOne("FreeBoardComment.maxStepInChild", freeBoardComment);
	}

	public int maxStepInPost(Object freeBoardComment) {
		return sqlSessionTemplate.selectOne("FreeBoardComment.maxStepInPost", freeBoardComment);
	}

	public int maxStepInDepth(Object freeBoardComment) {
		return sqlSessionTemplate.selectOne("FreeBoardComment.maxStepInDepth", freeBoardComment);
	}

	public Object select(int free_board_comment_idx) {
		return sqlSessionTemplate.selectOne("FreeBoardComment.select", free_board_comment_idx);
	}

	public void deleteAllByBoard(int free_board_idx) throws FreeBoardCommentException{
		int totalCount = totalCount(free_board_idx);
		int result = sqlSessionTemplate.delete("FreeBoardComment.deleteAllByBoard", free_board_idx);
		if(totalCount != result) throw new FreeBoardCommentException("자유게시판 해당 게시글에서 댓글 전체 삭제 실패");
	}

}
