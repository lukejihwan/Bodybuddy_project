package com.edu.bodybuddy.model.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.board.CounsellingBoardComment;
import com.edu.bodybuddy.exception.CounsellingBoardCommentException;
import com.edu.bodybuddy.exception.CounsellingBoardCommentException;


@Repository
public class MybatisCounsellingBoardCommentDAO implements BoardCommentDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAllByBoard(int counselling_board_idx) {
		return sqlSessionTemplate.selectList("CounsellingBoardComment.selectAllByBoard", counselling_board_idx);
	}

	public List selectAllByMember(int member_idx) {
		return sqlSessionTemplate.selectList("CounsellingBoardComment.selectAllByMember", member_idx);
	}

	public void insert(Object counsellingBoardComment) throws CounsellingBoardCommentException{
		int result = sqlSessionTemplate.insert("CounsellingBoardComment.insert", counsellingBoardComment); 
		if(result < 1) throw new CounsellingBoardCommentException("고민상담게시판 댓글 등록 실패");
	}

	public void update(Object counsellingBoardComment) throws CounsellingBoardCommentException{
		int result = sqlSessionTemplate.update("CounsellingBoardComment.update", counsellingBoardComment); 
		if(result < 1) throw new CounsellingBoardCommentException("고민상담게시판 댓글 수정 실패");
	}

	public void delete(int counselling_board_comment_idx) throws CounsellingBoardCommentException{
		int result = sqlSessionTemplate.delete("CounsellingBoardComment.delete", counselling_board_comment_idx); 
		if(result < 1) throw new CounsellingBoardCommentException("고민상담게시판 댓글 삭제 실패");
	}

	public int totalCount(int counselling_board_idx) {
		return sqlSessionTemplate.selectOne("CounsellingBoardComment.totalCount", counselling_board_idx);
	}

	public int maxStep(int counselling_board_idx) {
		return sqlSessionTemplate.selectOne("CounsellingBoardComment.maxStep", counselling_board_idx);
	}

	public void shiftAboveSteps(Object object) throws CounsellingBoardCommentException{
		CounsellingBoardComment counsellingBoardComment = (CounsellingBoardComment)object; 
		int countAboveSteps = sqlSessionTemplate.selectOne("CounsellingBoardComment.countAboveSteps", counsellingBoardComment);
		int result = sqlSessionTemplate.update("CounsellingBoardComment.shiftAboveSteps", counsellingBoardComment);
		if(
				countAboveSteps < result ||
				countAboveSteps > result) {
			throw new CounsellingBoardCommentException("대댓글 삽입을 위해 step 올리기 실패");
		}
	}
	public void unshiftAboveSteps(Object object) throws CounsellingBoardCommentException{
		CounsellingBoardComment counsellingBoardComment = (CounsellingBoardComment)object; 
		int countAboveSteps = sqlSessionTemplate.selectOne("CounsellingBoardComment.countAboveSteps", counsellingBoardComment);
		int result = sqlSessionTemplate.update("CounsellingBoardComment.unshiftAboveSteps", counsellingBoardComment);
		if(
				countAboveSteps < result ||
				countAboveSteps > result) {
			throw new CounsellingBoardCommentException("대댓글 삽입을 위해 step 올리기 실패");
		}
	}

	public int maxStepInChild(Object counsellingBoardComment) {
		return sqlSessionTemplate.selectOne("CounsellingBoardComment.maxStepInChild", counsellingBoardComment);
	}

	public int maxStepInPost(Object counsellingBoardComment) {
		return sqlSessionTemplate.selectOne("CounsellingBoardComment.maxStepInPost", counsellingBoardComment);
	}

	public int maxStepInDepth(Object counsellingBoardComment) {
		return sqlSessionTemplate.selectOne("CounsellingBoardComment.maxStepInDepth", counsellingBoardComment);
	}

	public Object select(int counselling_board_comment_idx) {
		return sqlSessionTemplate.selectOne("CounsellingBoardComment.select", counselling_board_comment_idx);
	}

	public void deleteAllByBoard(int counselling_board_idx) throws CounsellingBoardCommentException{
		int totalCount = totalCount(counselling_board_idx);
		int result = sqlSessionTemplate.delete("CounsellingBoardComment.deleteAllByBoard", counselling_board_idx);
		if(totalCount != result) throw new CounsellingBoardCommentException("고민상담게시판 해당 게시글에서 댓글 전체 삭제 실패");
	}
}
