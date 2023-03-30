package com.edu.bodybuddy.model.member;

import java.util.List;

import com.edu.bodybuddy.domain.member.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.domain.member.Role;
import com.edu.bodybuddy.exception.AddressException;
import com.edu.bodybuddy.exception.MemberException;
import com.edu.bodybuddy.exception.PasswordException;
import com.edu.bodybuddy.util.EmailManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberDAO memberDAO;
	private final AddressDAO addressDAO;
	private final PasswordDAO passwordDAO;
	private final BCryptPasswordEncoder passwordEncoder;
	private final EmailManager emailManager;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void emailCheck(Member member) throws MemberException {
		Member byEmail = memberDAO.selectByEmail(member);
		if(byEmail != null) throw new MemberException("이미 가입된 메일주소입니다");
	}

	@Override
	public void nicknameCheck(Member member) throws MemberException {
		Member byNickname = memberDAO.selectByNickname(member);
		if(byNickname != null) throw new MemberException("이미 사용중인 닉네임입니다");
	}
	
	@Override
	public Member selectByEmail(Member member) {
		return memberDAO.selectByEmail(member);
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
		//홈페이지 회원만 비밀번호를 기입하므로 암호화를 해준다
		if(member.getProvider().equals("home")) {
			String encodedPass = passwordEncoder.encode(member.getPassword().getPass());
			member.getPassword().setPass(encodedPass);
			memberDAO.insert(member);
			passwordDAO.insert(member);
		} else {
			memberDAO.insert(member);
		}

		//회원가입이 모두 완료되었을 경우 축하 메시지 발송
		emailManager.send(member);
	}

	@Override
	@Transactional
	public void update(Member member) throws MemberException, AddressException, PasswordException{
		//업데이트 진행
		memberDAO.update(member);
		//비밀번호가 변경되었을 경우 비밀번호 업데이트
		if(member.getPassword()!=null){
			String encodedPass = passwordEncoder.encode(member.getPassword().getPass());
			member.getPassword().setPass(encodedPass);
			passwordDAO.update(member);
		}
		//주소가 입력되었는지 확인
		if(member.getAddress()==null) return;
		
		//주소를 확인해서 기존 주소가 있으면 insert, 없으면 update
		Address address = addressDAO.selectByMember(member.getMember_idx());
		if(address == null){
			addressDAO.insert(member);
		} else {
			addressDAO.update(member);
		}
	}

	@Override
	public void delete(Member member) throws MemberException {
		passwordDAO.delete(member);
		memberDAO.delete(member);
	}
}
