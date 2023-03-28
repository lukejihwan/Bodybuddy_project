package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import com.edu.bodybuddy.domain.mypage.Report;

public interface ReportService {
	public int selectTotal(int member_idx);
	public List<Report> getList(Map map);
	public Report select(int report_idx);
	public void regist(Report report);
	public void update(Report report);
	public void delete(Report report);
}
