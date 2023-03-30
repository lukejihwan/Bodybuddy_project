package com.edu.bodybuddy.model.main;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisBoardRankDAO implements BoardRankDAO{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectWeeklyRankByRecommend(HashMap<String, Object> map) {
		return sqlSessionTemplate.selectList("Board.selectWeeklyRankByRecommend", map);
	}

	public int commentCount(HashMap<String, Object> map){
		int commentCount = 0;
		try {
			commentCount = sqlSessionTemplate.selectOne("Board.commentCount", map);
		} catch (BadSqlGrammarException e) {
			logger.warn(map.get("boardName") + " 테이블에 연결되는 Comment 테이블이 존재하지 않음");
			commentCount = 0;
		}
		return commentCount;
	}

}
