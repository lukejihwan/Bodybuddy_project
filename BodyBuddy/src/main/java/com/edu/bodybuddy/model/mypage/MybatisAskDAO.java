package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.mypage.Ask;
import com.edu.bodybuddy.exception.AskException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisAskDAO implements AskDAO{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int selectTotal(int member_idx) {
		return sqlSessionTemplate.selectOne("Ask.selectTotal", member_idx);
	}
	
	@Override
	public List<Ask> selectByMember(Map map) {
		log.info("들어오는 map은 : "+map);
		return sqlSessionTemplate.selectList("Ask.selectByMember", map);
	}

	@Override
	public Ask select(int ask_idx) {
		log.info("조회하려는 ask_idx는 : "+ask_idx);
		return sqlSessionTemplate.selectOne("Ask.select", ask_idx);
	}

	@Override
	public void insert(Ask ask) throws AskException {
		int result = sqlSessionTemplate.insert("Ask.insert", ask);
		if(result<1) throw new AskException("문의글 등록 실패");
		
	}

	@Override
	public void update(Ask ask) throws AskException {
		int result = sqlSessionTemplate.update("Ask.update", ask);
		if(result<1) throw new AskException("문의글 수정 실패");
		
	}

	@Override
	public void delete(Ask ask) throws AskException {
		int result = sqlSessionTemplate.delete("Ask.delete", ask);
		if(result<1) throw new AskException("문의글 삭제 실패");
		
	}
}
