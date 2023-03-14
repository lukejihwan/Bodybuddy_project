package com.edu.bodybuddy.model.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.domain.member.Address;
import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.member.Role;
import com.edu.bodybuddy.exception.AddressException;
import com.edu.bodybuddy.exception.MemberException;
import com.edu.bodybuddy.exception.PasswordException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberDAO memberDAO;
	private final AddressDAO addressDAO;
	private final PasswordDAO passwordDAO;
	private final BCryptPasswordEncoder passwordEncoder;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void nicknameCheck(Member member) throws MemberException {
		Member byEmail = memberDAO.selectByEmail(member);
		if(byEmail != null) throw new MemberException("이미 가입된 메일주소입니다");
	}

	@Override
	public void emailCheck(Member member) throws MemberException {
		Member byNickname = memberDAO.selectByNickname(member);
		if(byNickname != null) throw new MemberException("이미 사용중인 닉네임입니다");
	}
	
	@Override
	public List<Member> getMemberList() {
		return memberDAO.selectAll();
	}

	@Override
	@Transactional
	public void regist(Member member) throws MemberException, AddressException, PasswordException {
		//email 중복검사
		Member byEmail = memberDAO.selectByEmail(member);
		if(byEmail != null) throw new MemberException("이미 가입된 메일주소입니다");
		
		//nickname 중복검사
		Member byNickname = memberDAO.selectByNickname(member);
		if(byNickname != null) throw new MemberException("이미 사용중인 닉네임입니다");
		
		//중복 없을 경우 등록 진행
		//가입시 유저 권한 부여
		member.setRole(Role.ROLE_USER); 
		//비밀번호 암호화
		String encodedPass = passwordEncoder.encode(member.getPassword().getPass());
        member.getPassword().setPass(encodedPass);
        
        //insert
		memberDAO.insert(member);
		passwordDAO.insert(member);
		
		//주소 입력되었을 경우 주소도 기입
		Address address = member.getAddress();
		if(!address.getMember_address().equals("")) {
			address.setMember_idx(member.getMember_idx());
			addressDAO.insert(address);
		}
		
	}

	@Override
	public void update(Member member) throws MemberException, AddressException, PasswordException{
		//닉네임 중복 확인
		memberDAO.selectByNickname(member);
		
		//업데이트 진행
		memberDAO.update(member);
		
		//주소가 입력되었는지 아닌지를 판단하여 insert, update, delete 판단
		Address dto = addressDAO.selectByMember(member.getMember_idx());
		if(dto != null && member.getAddress()!=null) {
			addressDAO.update(member.getAddress());
		} else if(dto != null && member.getAddress()==null) {
			//기존 주소가 있었으나 지운 경우 delete 실행
			addressDAO.delete(member.getMember_idx());
		} else if(dto == null && member.getAddress()!=null) {
			addressDAO.insert(member.getAddress());
		}
	}

	@Override
	public void updatePass(Member member) throws MemberException, PasswordException {
		//비밀번호를 암호화 후 저장
		String encodedPass = passwordEncoder.encode(member.getPassword().getPass());
        member.getPassword().setPass(encodedPass);
		memberDAO.updatePass(member);
	}

	@Override
	public void delete(int member_idx) throws MemberException {
		memberDAO.delete(member_idx);
	}

}
