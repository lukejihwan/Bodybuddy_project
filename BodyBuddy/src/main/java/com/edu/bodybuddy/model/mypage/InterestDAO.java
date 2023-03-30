package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.mypage.Interest;

public interface InterestDAO {
    public int selectTotal(Member member);
    public List<Interest> selectByMember(Map map);
    public void insertExrRoutine(Interest interest);
    public void insertExrTip(Interest interest);
    public void insertDietShare(Interest interest);
    public void insertDietTip(Interest interest);
    public void deleteER(Interest interest);
    public void deleteET(Interest interest);
    public void deleteDS(Interest interest);
    public void deleteDT(Interest interest);
}
