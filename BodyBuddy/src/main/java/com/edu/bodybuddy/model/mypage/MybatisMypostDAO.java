package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.mypage.Mypost;

@Repository
public class MybatisMypostDAO implements MypostDAO{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int selectTotal(Member member) {
		
		return sqlSessionTemplate.selectOne("Mypost.selectTotal", member);
	}

	@Override
	public List<Mypost> selectMypost(Map map) {
		log.info("목록조회를 위한 파라미터: "+map);
		return sqlSessionTemplate.selectList("Mypost.selectMypost", map);
	}

	

}
