package com.edu.bodybuddy.model.mypage;

import java.util.List;

import com.edu.bodybuddy.domain.mypage.Report;

public interface ReportDAO {
	public List<Report> selectAll();
	public Report select(int report_idx);
	public void insert(Report report);
	public void update(Report report);
	public void delete(int report_idx);
}
