package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.exr.ExrTip;
import com.edu.bodybuddy.exception.ExrTipException;

@Service
public class ExrTipServiceImpl implements ExrTipService{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExrTipDAO exrTipDAO;
	
	@Override
	public List selectAll() {
		return exrTipDAO.selectAll();
	}

	@Override
	public ExrTip select(int exr_tip_idx) {
		return exrTipDAO.select(exr_tip_idx);
	}

	@Override
	public void insert(ExrTip exrTip) throws ExrTipException{
		exrTipDAO.insert(exrTip);
	}

	@Override
	public void update(ExrTip exrTip) throws ExrTipException{
		logger.info("팁 확인 "+exrTip);
		exrTipDAO.update(exrTip);
	}

	@Override
	public void delete(int exr_tip_idx) throws ExrTipException{
		exrTipDAO.delete(exr_tip_idx);
	}

	@Override
	public void plusHit(int exr_tip_idx) throws ExrTipException{
		exrTipDAO.plusHit(exr_tip_idx);
	}

	@Override
	public void plusRecommend(int exr_tip_idx) throws ExrTipException{
		exrTipDAO.plusRecommend(exr_tip_idx);
	}


}
