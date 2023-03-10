package com.edu.bodybuddy.model.diet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.diet.DietCategory;
import com.edu.bodybuddy.exception.DietCategoryException;

@Service
public class DietCategoryServiceImpl implements DietCategoryService{

	@Autowired
	private DietCategoryDAO diet_CategoryDAO;
	
	@Override
	public List selectAll() {
		return diet_CategoryDAO.selectAll();
	}

	@Override
	public DietCategory select(int diet_category_idx) {
		return diet_CategoryDAO.select(diet_category_idx);
	}

	@Override
	public void insert(DietCategory diet_Category) throws DietCategoryException{
		diet_CategoryDAO.insert(diet_Category);
	}

	@Override
	public void update(DietCategory diet_Category) throws DietCategoryException{
		diet_CategoryDAO.update(diet_Category);
	}

	@Override
	public void delete(int diet_category_idx) throws DietCategoryException{
		diet_CategoryDAO.delete(diet_category_idx);
	}

}