package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.mypage.Report;
import com.edu.bodybuddy.exception.ReportException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisReportDAO implements ReportDAO{
	private Logger log = LoggerFactory.getLogger(getClass());
	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int selectTotal(int member_idx) {
		return sqlSessionTemplate.selectOne("Report.selectTotal", member_idx);
	}

	@Override
	public List<Report> selectAll(Map map) {
		List<Report> list = sqlSessionTemplate.selectList("Report.selectByMember", map);
		log.info("넘어온 리포트리스트는 " +list);
		return list;
	}

	@Override
	public Report select(int report_idx) {
		return sqlSessionTemplate.selectOne("Report.select", report_idx);
	}

	@Override
	public void insert(Report report) throws ReportException{
		int result = sqlSessionTemplate.insert("Report.insert", report);
		if(result<1) throw new ReportException("신고글 등록 실패");
	}

	@Override
	public void update(Report report) throws ReportException{
		int result = sqlSessionTemplate.update("Report.update", report);
		if(result<1) throw new ReportException("신고글 수정 실패");
	}

	@Override
	public void delete(Report report) throws ReportException{
		int result = sqlSessionTemplate.delete("Report.delete", report);
		if(result<1) throw new ReportException("신고글 삭제 실패");
	}
	
}
