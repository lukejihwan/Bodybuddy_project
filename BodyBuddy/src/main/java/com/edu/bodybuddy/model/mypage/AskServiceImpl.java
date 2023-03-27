package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.mypage.Ask;
import com.edu.bodybuddy.exception.AskException;
@Service
public class AskServiceImpl implements AskService {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private AskDAO askDAO;
	
	@Override
	public int getTotal(int member_idx) {
		return askDAO.selectTotal(member_idx);
	}

	@Override
	public List<Ask> getList(Map map) {
		return askDAO.selectByMember(map);
	}

	@Override
	public Ask select(int ask_idx) {
		log.info("조회하려는 ask_idx는 : "+ask_idx);
		Ask ask = askDAO.select(ask_idx);
		log.info("조회결과 ask는 : " +ask);
		return ask;
	}

	@Override
	public void insert(Ask ask) throws AskException{
		askDAO.insert(ask);
	}

	@Override
	public void update(Ask ask) throws AskException {
		askDAO.update(ask);
	}

	@Override
	public void delete(Ask ask) throws AskException {
		askDAO.delete(ask);
	}

	

}
