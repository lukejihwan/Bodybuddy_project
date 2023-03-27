package com.edu.bodybuddy.model.mypage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.mypage.Report;
import com.edu.bodybuddy.exception.ReportException;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportDAO reportDAO;
    
    @Override
    public int selectTotal(int member_idx) {
    	return reportDAO.selectTotal(member_idx);
    } 
    
    @Override
    public List<Report> getList(Map map) {
        return reportDAO.selectAll(map);
    }

    @Override
    public Report select(int report_idx) {
        return reportDAO.select(report_idx);
    }

    @Override
    public void regist(Report report) throws ReportException {
        reportDAO.insert(report);
    }

    @Override
    public void update(Report report) throws ReportException{
        reportDAO.update(report);
    }

    @Override
    public void delete(Report report) throws ReportException{
        reportDAO.delete(report);
    }
}
