package com.edu.bodybuddy.model.exr;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.exr.ExrRoutine;
import com.edu.bodybuddy.exception.ExrRoutineException;

@Service
public class ExrRoutineServiceImpl implements ExrRoutineService{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrRoutineDAO exrRoutineDAO;

	@Override
	public List selectAll() {
		return exrRoutineDAO.selectAll();
	}
	@Override
	public ExrRoutine select(int exr_routine_idx) {
		return exrRoutineDAO.select(exr_routine_idx);
	}

	@Override
	public List selectBySearch(HashMap<String, String> map) {
		logger.info("전달받은 맵 객체"+map);
		return exrRoutineDAO.selectBySearch(map);
	}
	
	@Override
	public List selectAllByPage(int pg) {
		return exrRoutineDAO.selectAllByPage(pg);
	}
	
	@Override
	public List selectByFk(int exr_category_idx) {
		return exrRoutineDAO.selectByFk(exr_category_idx);
	}
	
	@Override
	public int totalCount() {
		return exrRoutineDAO.totalCount();
	}
	
	@Override
	public void insert(ExrRoutine exrRoutine) throws ExrRoutineException{
		exrRoutineDAO.insert(exrRoutine);
	}

	@Override
	public void update(ExrRoutine exrRoutine) throws ExrRoutineException{
		exrRoutineDAO.update(exrRoutine);
	}

	@Override
	public void delete(int exr_routine_idx) throws ExrRoutineException{
		exrRoutineDAO.delete(exr_routine_idx);
	}
	
	@Override
	public void plusHit(int exr_routine_idx) throws ExrRoutineException{
		exrRoutineDAO.plusHit(exr_routine_idx);
	}
	
	@Override
	public void plusRecommend(int exr_routine_idx) throws ExrRoutineException{
		exrRoutineDAO.plusRecommend(exr_routine_idx);
	}







}
