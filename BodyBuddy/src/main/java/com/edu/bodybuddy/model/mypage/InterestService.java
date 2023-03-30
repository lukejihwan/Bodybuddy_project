package com.edu.bodybuddy.model.mypage;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.mypage.Interest;

import java.util.List;
import java.util.Map;

public interface InterestService {
    public int selectTotal(Member member);
    public List<Interest> getInterestList(Map map);
    public void regist(Interest interest);
    public void delete(Interest interest);
}
