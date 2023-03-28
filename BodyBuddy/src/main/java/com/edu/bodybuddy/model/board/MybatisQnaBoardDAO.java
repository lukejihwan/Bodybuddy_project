package com.edu.bodybuddy.model.board;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.exception.QnaBoardException;


@Repository
public class MybatisQnaBoardDAO implements BoardDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("QnaBoard.selectAll");
	}

	public Object select(int qna_board_idx) {
		return sqlSessionTemplate.selectOne("QnaBoard.select", qna_board_idx);
	}

	public void insert(Object qnaBoard) throws QnaBoardException{
		int result = sqlSessionTemplate.insert("QnaBoard.insert", qnaBoard);
		if(result < 1) throw new QnaBoardException("QnA게시판 게시물 등록 실패");
	}

	public void update(Object qnaBoard) throws QnaBoardException{
		int result = sqlSessionTemplate.update("QnaBoard.update", qnaBoard);
		if(result < 1) throw new QnaBoardException("QnA게시판 게시물 수정 실패");
	}

	public void delete(int qna_board_idx) throws QnaBoardException{
		int result = sqlSessionTemplate.delete("QnaBoard.delete", qna_board_idx);
		if(result < 1) throw new QnaBoardException("QnA게시판 게시물 삭제 실패");
	}

	public List selectAllByPage(int page) {
		return sqlSessionTemplate.selectList("QnaBoard.selectAllByPage", page);
	}

	public int totalCount() {
		return sqlSessionTemplate.selectOne("QnaBoard.totalCount");
	}

	public void addHit(int qna_board_idx) throws QnaBoardException{
		int result = sqlSessionTemplate.update("QnaBoard.addHit", qna_board_idx);
		if(result < 1) throw new QnaBoardException("QnA게시판 조회수 추가 실패");
	}

	public void addRecommend(int qna_board_idx) throws QnaBoardException{
		int result = sqlSessionTemplate.update("QnaBoard.addRecommend", qna_board_idx);
		if(result < 1) throw new QnaBoardException("QnA게시판 추천 추가 실패");
	}

	public List selectAllBySearch(String value, int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("value", value);
		map.put("page", page);
		return sqlSessionTemplate.selectList("QnaBoard.selectAllBySearch", map);
	}

	public int totalCountSearch(String value) {
		return sqlSessionTemplate.selectOne("QnaBoard.totalCountSearch", value);
	}
	
}
