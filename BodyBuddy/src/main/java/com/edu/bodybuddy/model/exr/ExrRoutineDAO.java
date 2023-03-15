package com.edu.bodybuddy.model.exr;

import java.util.List;

import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.domain.exr.ExrRoutine;

public interface ExrRoutineDAO {
	public List selectAll();
	public ExrRoutine select(int exr_routine_idx);
	public void insert(ExrRoutine exrRoutine);
	public void update(ExrRoutine exrRoutine);
	public void delete(int exr_routine_idx);
}
