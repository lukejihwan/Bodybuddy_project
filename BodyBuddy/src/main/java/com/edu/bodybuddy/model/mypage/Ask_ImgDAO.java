package com.edu.bodybuddy.model.mypage;

import java.util.List;

import com.edu.bodybuddy.domain.mypage.Ask_img;

public interface Ask_ImgDAO {
	public List<Ask_img> selectAll(int ask_idx);
	public void insert(Ask_img ask_img);
	public void delete(int ask_idx);
}
