package com.edu.bodybuddy.model.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.board.QnaBoardComment;
import com.edu.bodybuddy.exception.QnaBoardCommentException;
import com.edu.bodybuddy.exception.QnaBoardCommentException;


@Repository
public class MybatisQnaBoardCommentDAO implements BoardCommentDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAllByBoard(int qna_board_idx) {
		return sqlSessionTemplate.selectList("QnaBoardComment.selectAllByBoard", qna_board_idx);
	}

	public List selectAllByMember(int member_idx) {
		return sqlSessionTemplate.selectList("QnaBoardComment.selectAllByMember", member_idx);
	}

	public void insert(Object qnaBoardComment) throws QnaBoardCommentException{
		int result = sqlSessionTemplate.insert("QnaBoardComment.insert", qnaBoardComment); 
		if(result < 1) throw new QnaBoardCommentException("QnA게시판 댓글 등록 실패");
	}

	public void update(Object qnaBoardComment) throws QnaBoardCommentException{
		int result = sqlSessionTemplate.update("QnaBoardComment.update", qnaBoardComment); 
		if(result < 1) throw new QnaBoardCommentException("QnA게시판 댓글 수정 실패");
	}

	public void delete(int qna_board_comment_idx) throws QnaBoardCommentException{
		int result = sqlSessionTemplate.delete("QnaBoardComment.delete", qna_board_comment_idx); 
		if(result < 1) throw new QnaBoardCommentException("QnA게시판 댓글 삭제 실패");
	}

	public int totalCount(int qna_board_idx) {
		return sqlSessionTemplate.selectOne("QnaBoardComment.totalCount", qna_board_idx);
	}

	public int maxStep(int qna_board_idx) {
		return sqlSessionTemplate.selectOne("QnaBoardComment.maxStep", qna_board_idx);
	}

	public void shiftAboveSteps(Object object) throws QnaBoardCommentException{
		QnaBoardComment qnaBoardComment = (QnaBoardComment)object; 
		int countAboveSteps = sqlSessionTemplate.selectOne("QnaBoardComment.countAboveSteps", qnaBoardComment);
		int result = sqlSessionTemplate.update("QnaBoardComment.shiftAboveSteps", qnaBoardComment);
		if(
				countAboveSteps < result ||
				countAboveSteps > result) {
			throw new QnaBoardCommentException("대댓글 삽입을 위해 step 올리기 실패");
		}
	}
	public void unshiftAboveSteps(Object object) throws QnaBoardCommentException{
		QnaBoardComment qnaBoardComment = (QnaBoardComment)object; 
		int countAboveSteps = sqlSessionTemplate.selectOne("QnaBoardComment.countAboveSteps", qnaBoardComment);
		int result = sqlSessionTemplate.update("QnaBoardComment.unshiftAboveSteps", qnaBoardComment);
		if(
				countAboveSteps < result ||
				countAboveSteps > result) {
			throw new QnaBoardCommentException("대댓글 삽입을 위해 step 올리기 실패");
		}
	}

	public int maxStepInChild(Object qnaBoardComment) {
		return sqlSessionTemplate.selectOne("QnaBoardComment.maxStepInChild", qnaBoardComment);
	}

	public int maxStepInPost(Object qnaBoardComment) {
		return sqlSessionTemplate.selectOne("QnaBoardComment.maxStepInPost", qnaBoardComment);
	}

	public int maxStepInDepth(Object qnaBoardComment) {
		return sqlSessionTemplate.selectOne("QnaBoardComment.maxStepInDepth", qnaBoardComment);
	}

	public Object select(int qna_board_comment_idx) {
		return sqlSessionTemplate.selectOne("QnaBoardComment.select", qna_board_comment_idx);
	}
	
	public void deleteAllByBoard(int qna_board_idx) throws QnaBoardCommentException{
		int totalCount = totalCount(qna_board_idx);
		int result = sqlSessionTemplate.delete("QnaBoardComment.deleteAllByBoard", qna_board_idx);
		if(totalCount != result) throw new QnaBoardCommentException("QnA게시판 해당 게시글에서 댓글 전체 삭제 실패");
	}
}
