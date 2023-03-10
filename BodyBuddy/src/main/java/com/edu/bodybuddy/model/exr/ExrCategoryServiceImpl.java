package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.exr.ExrCategory;
import com.edu.bodybuddy.exception.ExrCategoryException;

@Service
public class ExrCategoryServiceImpl implements ExrCategoryService{
	@Autowired
	private ExrCategoryDAO exrCategoryDAO;

	@Override
	public List selectAll() {
		return exrCategoryDAO.selectAll();
	}

	@Override
	public ExrCategory select(int exr_category_idx) {
		return exrCategoryDAO.select(exr_category_idx);
	}

	@Override
	public void insert(ExrCategory exrCategory) throws ExrCategoryException{
		
		
		exrCategoryDAO.insert(exrCategory);
	}

	@Override
	public void update(ExrCategory exrCategory) throws ExrCategoryException{
		exrCategoryDAO.update(exrCategory);
	}

	@Override
	public void delete(int exr_category_idx) throws ExrCategoryException{
		exrCategoryDAO.delete(exr_category_idx);
	}

}
