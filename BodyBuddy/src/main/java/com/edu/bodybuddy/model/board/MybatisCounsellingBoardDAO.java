package com.edu.bodybuddy.model.board;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.exception.CounsellingBoardException;


@Repository
public class MybatisCounsellingBoardDAO implements BoardDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("CounsellingBoard.selectAll");
	}

	public Object select(int counselling_board_idx) {
		return sqlSessionTemplate.selectOne("CounsellingBoard.select", counselling_board_idx);
	}

	public void insert(Object counsellingBoard) throws CounsellingBoardException{
		int result = sqlSessionTemplate.insert("CounsellingBoard.insert", counsellingBoard);
		if(result < 1) throw new CounsellingBoardException("고민상담게시판 게시물 등록 실패");
	}

	public void update(Object counsellingBoard) throws CounsellingBoardException{
		int result = sqlSessionTemplate.update("CounsellingBoard.update", counsellingBoard);
		if(result < 1) throw new CounsellingBoardException("고민상담게시판 게시물 수정 실패");
	}

	public void delete(int counselling_board_idx) throws CounsellingBoardException{
		int result = sqlSessionTemplate.delete("CounsellingBoard.delete", counselling_board_idx);
		if(result < 1) throw new CounsellingBoardException("고민상담게시판 게시물 삭제 실패");
	}

	public List selectAllByPage(int page) {
		return sqlSessionTemplate.selectList("CounsellingBoard.selectAllByPage", page);
	}

	public int totalCount() {
		return sqlSessionTemplate.selectOne("CounsellingBoard.totalCount");
	}

	public void addHit(int counselling_board_idx) throws CounsellingBoardException{
		int result = sqlSessionTemplate.update("CounsellingBoard.addHit", counselling_board_idx);
		if(result < 1) throw new CounsellingBoardException("고민상담게시판 조회수 추가 실패");
	}

	public void addRecommend(int counselling_board_idx) throws CounsellingBoardException{
		int result = sqlSessionTemplate.update("CounsellingBoard.addRecommend", counselling_board_idx);
		if(result < 1) throw new CounsellingBoardException("고민상담게시판 추천 추가 실패");
	}

	public List selectAllBySearch(String value, int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("value", value);
		map.put("page", page);
		return sqlSessionTemplate.selectList("CounsellingBoard.selectAllBySearch", map);
	}

	public int totalCountSearch(String value) {
		return sqlSessionTemplate.selectOne("CounsellingBoard.totalCountSearch", value);
	}
	
}
