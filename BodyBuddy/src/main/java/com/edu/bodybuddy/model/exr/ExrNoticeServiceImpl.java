package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.exception.ExrNoticeException;

@Service
public class ExrNoticeServiceImpl  implements ExrNoticeService{
	@Autowired
	private ExrNoticeDAO exrNoticeDAO;
	
	@Override
	public List selectAll() {
		return exrNoticeDAO.selectAll();
	}

	@Override
	public ExrNotice select(int exr_notice_idx) {
		return exrNoticeDAO.select(exr_notice_idx);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(ExrNotice exrNotice) throws ExrNoticeException{
		exrNoticeDAO.insert(exrNotice);
	}
	
	@Override
	public void update(ExrNotice exrNotice) throws ExrNoticeException{
		exrNoticeDAO.update(exrNotice);
	}

	@Override
	public void delete(int exr_notice_idx) throws ExrNoticeException{
		exrNoticeDAO.delete(exr_notice_idx);
	}

}
