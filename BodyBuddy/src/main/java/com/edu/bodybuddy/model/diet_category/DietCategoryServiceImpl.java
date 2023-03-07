package com.edu.bodybuddy.model.diet_category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.diet.Diet_Category;
import com.edu.bodybuddy.exception.Diet_CategoryException;

@Service
public class DietCategoryServiceImpl implements Diet_CategoryService{

	@Autowired
	private Diet_CategoryDAO diet_CategoryDAO;
	
	@Override
	public List selectAll() {
		return diet_CategoryDAO.selectAll();
	}

	@Override
	public Diet_Category select(int diet_category_idx) {
		return diet_CategoryDAO.select(diet_category_idx);
	}

	@Override
	public void insert(Diet_Category diet_Category) throws Diet_CategoryException{
		diet_CategoryDAO.insert(diet_Category);
	}

	@Override
	public void update(Diet_Category diet_Category) throws Diet_CategoryException{
		diet_CategoryDAO.update(diet_Category);
	}

	@Override
	public void delete(int diet_category_idx) throws Diet_CategoryException{
		diet_CategoryDAO.delete(diet_category_idx);
	}

}
