package com.edu.bodybuddy.domain.member;

import lombok.Data;

@Data
public class GroupMember {
	private int group_member_idx;
	private int member_idx;
	private Group group;
}
