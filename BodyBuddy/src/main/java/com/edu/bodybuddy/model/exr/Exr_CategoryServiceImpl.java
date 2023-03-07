package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.exr.Exr_Category;
import com.edu.bodybuddy.exception.Exr_CategoryException;

@Service
public class Exr_CategoryServiceImpl implements Exr_CategoryService{
	@Autowired
	private Exr_CategoryDAO exr_CategoryDAO;

	@Override
	public List selectAll() {
		return exr_CategoryDAO.selectAll();
	}

	@Override
	public Exr_Category select(int exr_Category_idx) {
		return exr_CategoryDAO.select(exr_Category_idx);
	}

	@Override
	public void insert(Exr_Category exr_Category) throws Exr_CategoryException{
		exr_CategoryDAO.insert(exr_Category);
	}

	@Override
	public void update(Exr_Category exr_Category) throws Exr_CategoryException{
		exr_CategoryDAO.update(exr_Category);
	}

	@Override
	public void delete(int exr_Category_idx) throws Exr_CategoryException{
		exr_CategoryDAO.delete(exr_Category_idx);
	}

}
