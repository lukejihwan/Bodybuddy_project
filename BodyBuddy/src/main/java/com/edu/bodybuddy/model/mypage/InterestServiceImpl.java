package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.mypage.Interest;

@Service
public class InterestServiceImpl implements InterestService{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
    private InterestDAO interestDAO;

    @Override
    public int selectTotal(Member member) {
        return interestDAO.selectTotal(member);
    }

    @Override
    public List<Interest> getInterestList(Map map) {
    	log.info("목록 조회를 위한 파라미터 map : " + map);
        return null;
    }

    @Override
    public void regist(Interest interest) {
        String table_name = interest.getTable_name();
        switch (table_name){
            case "운동루틴" : {
                interestDAO.insertExrRoutine(interest); break;
            }
            case "운동팁" : {
                interestDAO.insertExrTip(interest); break;
            }
            case "식단공유" : {
                interestDAO.insertDietShare(interest); break;
            }
            case "식단팁" : {
                interestDAO.insertDietTip(interest); break;
            }
        }
    }

    @Override
    public void delete(Interest interest) {
        String table_name = interest.getTable_name();
        switch (table_name){
            case "운동루틴" : {
                interestDAO.deleteER(interest); break;
            }
            case "운동팁" : {
                interestDAO.deleteET(interest); break;
            }
            case "식단공유" : {
                interestDAO.deleteDS(interest); break;
            }
            case "식단팁" : {
                interestDAO.deleteDT(interest); break;
            }
        }
    }
}
