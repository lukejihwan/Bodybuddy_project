package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.exr.ExrTip;
import com.edu.bodybuddy.domain.exr.ExrToday;
import com.edu.bodybuddy.exception.ExrRoutineException;
import com.edu.bodybuddy.exception.ExrTipException;
import com.edu.bodybuddy.exception.ExrTodayException;

@Service
public class ExrTodayServiceImpl implements ExrTodayService{
	@Autowired
	private ExrTodayDAO exrTodayDAO;
	
	@Override
	public List selectAll() {
		return exrTodayDAO.selectAll();
	}

	@Override
	public ExrToday select(int exr_today_idx) {
		return exrTodayDAO.select(exr_today_idx);
	}

	@Override
	public void insert(ExrToday exrToday) throws ExrTodayException{
		exrTodayDAO.insert(exrToday);
	}

	@Override
	public void update(ExrToday exrToday) throws ExrTodayException{
		exrTodayDAO.update(exrToday);
	}

	@Override
	public void delete(int exr_today_idx) throws ExrTodayException{
		exrTodayDAO.delete(exr_today_idx);
	}

	@Override
	public void plusHit(int exr_today_idx) throws ExrTodayException{
		exrTodayDAO.plusHit(exr_today_idx);
	}

	@Override
	public void plusRecommend(int exr_today_idx) throws ExrTodayException{
		exrTodayDAO.plusRecommend(exr_today_idx);
	}


}
