package com.edu.bodybuddy.model.diet;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.diet.DietTip;
import com.edu.bodybuddy.exception.DietTipException;

@Repository
public class MybatisDietTipDAO implements DietTipDAO{

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("DietTip.selectAll");
	}

	@Override
	public DietTip select(int diet_tip_idx) {
		return sqlSessionTemplate.selectOne("DietTip.select",diet_tip_idx);
	}

	@Override
	public void insert(DietTip dietTip) throws DietTipException{
		int result=sqlSessionTemplate.insert("DietTip.insert", dietTip);
		if(result<1) {
			throw new DietTipException("팁 게시판 글 등록 실패"); 
		}	
	}

	@Override
	public void update(DietTip dietTip) throws DietTipException{
		int result=sqlSessionTemplate.update("DietTip.update", dietTip);
		if(result<1) {
			throw new DietTipException("팁 게시판 글 수정 실패"); 
		}
	}

	@Override
	public void delete(int diet_tip_idx) throws DietTipException{
		int result=sqlSessionTemplate.delete("DietTip.delete", diet_tip_idx);
		if(result<1) {
			throw new DietTipException("팁 게시판 글 삭제 실패"); 
		}
	}

	@Override
	public void addHit(int diet_tip_idx) throws DietTipException{
		int result=sqlSessionTemplate.update("DietTip.addHit", diet_tip_idx);
		if(result<1) {
			throw new DietTipException("조회수 추가 실패"); 
		}
	}

	@Override
	public void addRecommend(int diet_tip_idx) throws DietTipException{
		int result=sqlSessionTemplate.update("DietTip.addRecommend", diet_tip_idx);
		if(result<1) {
			throw new DietTipException("찜하기 등록 실패"); 
		}
	}

	@Override
	public List selectBySearch(HashMap<String, String> map) {
		return sqlSessionTemplate.selectList("DietTip.selectBySearch", map);
	}

	@Override
	public int totalRecord() {
		return sqlSessionTemplate.selectOne("DietTip.totalCount");
	}

	@Override
	public List selectAllPage(int page) {
		return sqlSessionTemplate.selectList("DietTip.selectAllPage", page);
	}

}
