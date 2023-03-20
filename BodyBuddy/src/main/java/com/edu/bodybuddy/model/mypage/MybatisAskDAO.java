package com.edu.bodybuddy.model.mypage;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.mypage.Ask;
import com.edu.bodybuddy.exception.AskException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisAskDAO implements AskDAO{
	
	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Ask> selectAll() {
		return sqlSessionTemplate.selectList("Ask.selectAll");
	}

	@Override
	public Ask select(int ask_idx) {
		return sqlSessionTemplate.selectOne("Ask.select", ask_idx);
	}

	@Override
	public void insert(Ask ask) {
		int result = sqlSessionTemplate.insert("Ask.insert", ask);
		if(result<1) throw new AskException("문의글 등록 실패");
		
	}

	@Override
	public void update(Ask ask) {
		int result = sqlSessionTemplate.update("Ask.update", ask);
		if(result<1) throw new AskException("문의글 수정 실패");
		
	}

	@Override
	public void delete(int ask_idx) {
		int result = sqlSessionTemplate.delete("Ask.delete", ask_idx);
		if(result<1) throw new AskException("문의글 삭제 실패");
		
	}

}
