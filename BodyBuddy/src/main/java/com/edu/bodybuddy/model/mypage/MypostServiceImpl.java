package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.mypage.Mypost;

@Service
public class MypostServiceImpl implements MypostService {
	
	@Autowired
	private MypostDAO mypostDAO;
	
	@Override
	public int selectTotal(Member member) {
		return mypostDAO.selectTotal(member);
	}
	
	@Override
	public List<Mypost> selectMypost(Map map) {
		return mypostDAO.selectMypost(map);
	}

	

}
