package com.edu.bodybuddy.model.diet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.diet.Diet_Category;
import com.edu.bodybuddy.exception.Diet_CategoryException;

@Service
public class DietCategoryServiceImpl implements DietCategoryService{

	@Autowired
	private DietCategoryDAO dietCategoryDAO;
	
	@Override
	public List selectAll() {
		return dietCategoryDAO.selectAll();
	}

	@Override
	public Diet_Category select(int diet_category_idx) {
		return dietCategoryDAO.select(diet_category_idx);
	}

	@Override
	public void insert(Diet_Category diet_Category) throws Diet_CategoryException{
		dietCategoryDAO.insert(diet_Category);
	}

	@Override
	public void update(Diet_Category diet_Category) throws Diet_CategoryException{
		dietCategoryDAO.update(diet_Category);
	}

	@Override
	public void delete(int diet_category_idx) throws Diet_CategoryException{
		dietCategoryDAO.delete(diet_category_idx);
	}

}
