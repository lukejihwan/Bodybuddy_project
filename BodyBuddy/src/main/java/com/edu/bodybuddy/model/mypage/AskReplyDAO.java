package com.edu.bodybuddy.model.mypage;

import com.edu.bodybuddy.domain.mypage.Ask;

public interface AskReplyDAO {
	public void insert(Ask ask);
	public void update(Ask ask);
	public void delete(Ask ask);
}
