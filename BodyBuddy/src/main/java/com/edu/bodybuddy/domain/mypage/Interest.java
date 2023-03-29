package com.edu.bodybuddy.domain.mypage;

import lombok.Data;

@Data
public class Interest {
	private int interest_idx;
	private int idx;
	private int member_idx;
	private String table_name;
	private String title;
	private String writer;
}
