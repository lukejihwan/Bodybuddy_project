package com.edu.bodybuddy.model.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.bodybuddy.domain.member.Address;
import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.MemberException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberDAO memberDAO;
	private final AddressDAO addressDAO;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	//로그인 체크
	public Member selectByIdPass(Member member) {
		return memberDAO.selectByIdPass(member);
	}

	@Override
	public List<Member> getMemberList() {
		return memberDAO.selectAll();
	}

	@Override
	public void regist(Member member) throws MemberException{
		//id, email 중복검사
		memberDAO.selectById(member);
		memberDAO.selectByEmail(member);
		
		//중복 없을 경우 등록 진행
		int member_idx = memberDAO.insert(member);
		
		//주소 입력되었을 경우 주소도 기입
		Address address = member.getAddress();
		if(address!=null) {
			address.setMember_idx(member_idx);
			addressDAO.insert(address);
		}
		
	}

	@Override
	public void update(Member member) {
		memberDAO.update(member);
		if(member.getAddress() == null) {
			memberDAO.delete(member.getMember_idx());
		} else {
			addressDAO.update(member.getAddress());
		}
		
	}

	@Override
	public void updatePass(Member member) {
		memberDAO.updatePass(member);
	}

	@Override
	public void delete(int member_idx) {
		memberDAO.delete(member_idx);
	}

}
