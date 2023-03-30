package com.edu.bodybuddy.domain.mypage;

import lombok.Data;

@Data
public class Interest {
	private String table_name;
	private int idx;
	private String title;
	private String regdate;
	private int member_idx;
}
