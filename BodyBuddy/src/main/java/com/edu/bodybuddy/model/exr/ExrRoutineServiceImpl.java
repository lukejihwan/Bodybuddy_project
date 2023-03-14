package com.edu.bodybuddy.model.exr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.exr.ExrRoutine;
import com.edu.bodybuddy.exception.ExrRoutineException;

@Service
public class ExrRoutineServiceImpl implements ExrRoutineService{
	@Autowired
	private ExrRoutineDAO exrRoutineDAO;
	
	@Override
	public ExrRoutine select(int exr_routine_idx) {
		return exrRoutineDAO.select(exr_routine_idx);
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

}
