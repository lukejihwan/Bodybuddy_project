package com.edu.bodybuddy.model.mypage;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.mypage.Ask_img;
import com.edu.bodybuddy.exception.ReportException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisAsk_imgDAO implements Ask_ImgDAO{
	
	private final SqlSessionTemplate sqlSessionTemplate; 

	@Override
	public List<Ask_img> selectAll(int ask_idx) {
		return sqlSessionTemplate.selectList("Ask_img.selectAll", ask_idx);
	}

	@Override
	public void insert(Ask_img ask_img) {
		int result = sqlSessionTemplate.insert("Ask_img.insert", ask_img);
		if(result<1) throw new ReportException("신고글 등록 실패");
		
	}

	@Override
	public void delete(int ask_idx) {
		int result = sqlSessionTemplate.delete("Ask_img.delete", ask_idx);
		if(result<1) throw new ReportException("신고글 삭제 실패");
		
	}

}
