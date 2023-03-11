package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.exr.ExrNoticeImg;
import com.edu.bodybuddy.exception.ExrNoticeImgException;

@Service
public class ExrNoticeImgServiceImpl implements ExrNoticeImgService{
	@Autowired
	private ExrNoticeImgDAO exrNoticeImgDAO;
	
	@Override
	public List selectByExrNotice(int exr_notice_idx) {
		return exrNoticeImgDAO.selectByExrNotice(exr_notice_idx);
	}

	@Override
	public void insert(ExrNoticeImg exrNoticeImg) throws ExrNoticeImgException{
		exrNoticeImgDAO.insert(exrNoticeImg);
	}

	@Override
	public void delete(int exr_notice_idx) throws ExrNoticeImgException{
		exrNoticeImgDAO.delete(exr_notice_idx);
	}

}
