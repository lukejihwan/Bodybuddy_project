package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.mypage.Ask;
import com.edu.bodybuddy.domain.mypage.Interest;
import com.edu.bodybuddy.exception.InterestException;

import lombok.RequiredArgsConstructor;

@Repository
public class MybatisInterestDAO implements InterestDAO{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int selectTotal(Member member) {
    	log.info("진입함"+ member);
    	int total= sqlSessionTemplate.selectOne("Interest.selectTotal", member);
    	log.info("총레코드 "+total);
        return total;
    }

    @Override
    public List<Interest> selectByMember(Map map) {
    	log.info("목록 조회를 위한 파라미터 map : " + map);
    	List<Interest> list = sqlSessionTemplate.selectList("Interest.selectInterest", map);
    	log.info("받아온 리스트: "+list);
        return list;
    }

    @Override
    public void insertExrRoutine(Interest interest) {
        int result = sqlSessionTemplate.insert("InterestExrRoutine.insert", interest);
        if(result<1){
            throw new InterestException("운동루틴 즐겨찾기 등록에 실패했습니다");
        }
    }

    @Override
    public void insertExrTip(Interest interest) {
        int result = sqlSessionTemplate.insert("InterestExrTip.insert", interest);
        if(result<1){
            throw new InterestException("운동팁 즐겨찾기 등록에 실패했습니다");
        }
    }

    @Override
    public void insertDietShare(Interest interest) {
        int result = sqlSessionTemplate.insert("InterestDietShare.insert", interest);
        if(result<1){
            throw new InterestException("식단 즐겨찾기 등록에 실패했습니다");
        }
    }

    @Override
    public void insertDietTip(Interest interest) {
        int result = sqlSessionTemplate.insert("InterestDietTip.insert", interest);
        if(result<1){
            throw new InterestException("식단 즐겨찾기 등록에 실패했습니다");
        }
    }

    @Override
    public void deleteER(Interest interest) {
        int result = sqlSessionTemplate.delete("InterestExrRoutine.delete", interest);
        if(result<1){
            throw new InterestException("운동 즐겨찾기 삭제에 실패했습니다");
        }
    }

    @Override
    public void deleteET(Interest interest) {
        int result = sqlSessionTemplate.delete("InterestExrTip.delete", interest);
        if(result<1){
            throw new InterestException("운동 즐겨찾기 삭제에 실패했습니다");
        }
    }

    @Override
    public void deleteDS(Interest interest) {
        int result = sqlSessionTemplate.delete("InterestDietShare.delete", interest);
        if(result<1){
            throw new InterestException("식단 즐겨찾기 삭제에 실패했습니다");
        }
    }

    @Override
    public void deleteDT(Interest interest) {
        int result = sqlSessionTemplate.delete("InterestDietTip.delete", interest);
        if(result<1){
            throw new InterestException("식단 즐겨찾기 삭제에 실패했습니다");
        }
    }
}
