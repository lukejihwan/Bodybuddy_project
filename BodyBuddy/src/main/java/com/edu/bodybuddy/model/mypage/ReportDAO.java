package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.mypage.Report;

public interface ReportDAO {
	public int selectTotal(int member_idx);
	public List<Report> selectAll(Map map);
	public Report select(int report_idx);
	public void insert(Report report);
	public void update(Report report);
	public void delete(Report report);
}
