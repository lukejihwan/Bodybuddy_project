package com.edu.bodybuddy.model.mypage;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.mypage.Report;
import com.edu.bodybuddy.exception.ReportException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisReportDAO implements ReportDAO{
	
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Report> selectAll() {
		return sqlSessionTemplate.selectList("Report.selectAll");
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
	public void delete(int report_idx) throws ReportException{
		int result = sqlSessionTemplate.delete("Report.delete", report_idx);
		if(result<1) throw new ReportException("신고글 삭제 실패");
	}
	
}
