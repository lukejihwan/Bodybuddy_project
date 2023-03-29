package com.edu.bodybuddy.model.board;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.exception.FreeBoardException;

@Repository
public class MybatisFreeBoardDAO implements BoardDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("FreeBoard.selectAll");
	}

	public Object select(int free_board_idx) {
		return sqlSessionTemplate.selectOne("FreeBoard.select", free_board_idx);
	}

	public void insert(Object freeBoard) throws FreeBoardException{
		int result = sqlSessionTemplate.insert("FreeBoard.insert", freeBoard);
		if(result < 1) throw new FreeBoardException("자유게시판 게시물 등록 실패");
	}

	public void update(Object freeBoard) throws FreeBoardException{
		int result = sqlSessionTemplate.update("FreeBoard.update", freeBoard);
		if(result < 1) throw new FreeBoardException("자유게시판 게시물 수정 실패");
	}

	public void delete(int free_board_idx) throws FreeBoardException{
		int result = sqlSessionTemplate.delete("FreeBoard.delete", free_board_idx);
		if(result < 1) throw new FreeBoardException("자유게시판 게시물 삭제 실패");
	}

	public List selectAllByPage(int page) {
		return sqlSessionTemplate.selectList("FreeBoard.selectAllByPage", page);
	}

	public int totalCount() {
		return sqlSessionTemplate.selectOne("FreeBoard.totalCount");
	}

	public void addHit(int free_board_idx) throws FreeBoardException{
		int result = sqlSessionTemplate.update("FreeBoard.addHit", free_board_idx);
		if(result < 1) throw new FreeBoardException("자유게시판 조회수 추가 실패");
	}

	public void addRecommend(int free_board_idx) throws FreeBoardException{
		int result = sqlSessionTemplate.update("FreeBoard.addRecommend", free_board_idx);
		if(result < 1) throw new FreeBoardException("자유게시판 추천 추가 실패");
	}

	public List selectAllBySearch(String value, int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("value", value);
		map.put("page", page);
		return sqlSessionTemplate.selectList("FreeBoard.selectAllBySearch", map);
	}

	public int totalCountSearch(String value) {
		return sqlSessionTemplate.selectOne("FreeBoard.totalCountSearch", value);
	}
	
}
