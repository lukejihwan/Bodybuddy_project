package com.edu.bodybuddy.model.mypage;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.mypage.Ask;
import com.edu.bodybuddy.exception.AskReplyException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisAskReplyDAO implements AskReplyDAO{
	
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Ask ask) throws AskReplyException{
		int result = sqlSessionTemplate.insert("AskReply.insert", ask);
		if(result < 1) {
			throw new AskReplyException("문의글 답변 등록 실패");
		}
	}

	@Override
	public void update(Ask ask) throws AskReplyException{
		int result = sqlSessionTemplate.update("AskReply.update", ask);
		if(result < 1) {
			throw new AskReplyException("문의글 답변 등록 실패");
		}
	}

	@Override
	public void delete(Ask ask) throws AskReplyException{
		int result = sqlSessionTemplate.delete("AskReply.delete", ask);
		if(result < 1) {
			throw new AskReplyException("문의글 답변 등록 실패");
		}
	}



}
