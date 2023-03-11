package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.domain.exr.ExrNotice;
import com.edu.bodybuddy.domain.exr.ExrNoticeImg;
import com.edu.bodybuddy.exception.ExrNoticeException;
import com.edu.bodybuddy.util.FileManager;

@Service
public class ExrNoticeServiceImpl  implements ExrNoticeService{
	@Autowired
	private ExrNoticeDAO exrNoticeDAO;
	@Autowired
	private ExrNoticeImgDAO exrNoticeImgDAO;
	@Autowired
	private FileManager fileManager;
	
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
	public void regist(ExrNotice exrNotice, String dir) throws ExrNoticeException{
		exrNoticeDAO.insert(exrNotice);
		
		// 사진 파일 로컬 저장
		List<String> filenameList=fileManager.save(exrNotice.getPhoto(), dir);
		
		// 파일 수만큼 dto 세팅해서 저장하기
		for(String filename:filenameList) {
			ExrNoticeImg exrNoticeImg=new ExrNoticeImg();
			exrNoticeImg.setExrNotice(exrNotice);
			exrNoticeImg.setFilename(filename);
			exrNoticeImgDAO.insert(exrNoticeImg);
		}
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
