package com.edu.bodybuddy.model.security;

import com.edu.bodybuddy.domain.security.MemberDetail;

public interface SocialLoginService {
	public String getGrantUrl();
	public MemberDetail getSocialInfo(String code);
}
