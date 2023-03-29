package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.mypage.Mypost;

public interface MypostService {
	public int selectTotal(Member member);
	public List<Mypost> selectMypost(Map map);
}
