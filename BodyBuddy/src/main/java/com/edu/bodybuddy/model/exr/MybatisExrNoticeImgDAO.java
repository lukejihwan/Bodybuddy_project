package com.edu.bodybuddy.model.exr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.bodybuddy.domain.exr.ExrNoticeImg;
import com.edu.bodybuddy.exception.ExrNoticeImgException;

@Repository
public class MybatisExrNoticeImgDAO implements ExrNoticeImgDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectByExrNotice(int exr_notice_idx) {
		return sqlSessionTemplate.selectList("ExrNoticeImg.selectByExrNotice", exr_notice_idx);
	}

	@Override
	public void insert(ExrNoticeImg exrNoticeImg) throws ExrNoticeImgException{
		int result=sqlSessionTemplate.insert("ExrNoticeImg.insert", exrNoticeImg);
		if(result<1) {
			throw new ExrNoticeImgException("사진 등록 실패");
		}
	}

	@Override
	public void delete(int exr_notice_idx) throws ExrNoticeImgException{
		int result=sqlSessionTemplate.delete("ExrNoticeImg.delete", exr_notice_idx);
		if(result<1) {
			throw new ExrNoticeImgException("사진 삭제 실패");
		}
	}

}
