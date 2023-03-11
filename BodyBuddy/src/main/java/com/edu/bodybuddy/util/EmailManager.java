package com.edu.bodybuddy.util;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.EmailException;


public class EmailManager {
	@Autowired
	private JavaMailSender javaMailSender;
	
	//메일보내기
	public void send(Member member) throws EmailException{
		//이메일 정보를 담을 메시지 객체 생성
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		try {
			//받을 사람 정보 설정
			msg.addRecipient(RecipientType.TO, new InternetAddress(member.getEmail()));
			//보내는 사람 정보 설정
			msg.addFrom(new InternetAddress[] {
					new InternetAddress("winter0927@gmail.com","webmaster")
			});
			
			//메일 제목
			msg.setSubject("BodyBuddy 회원가입이 완료되었습니다");
			msg.setText("회원 가입을 축하드립니다", "UTF-8");
			
			//메일 전송
			javaMailSender.send(msg);
		} catch (AddressException e) {
			throw new EmailException("이메일 받는 사람 정보 설정 실패", e);
		} catch (MessagingException e) {
			throw new EmailException("이메일 받는 사람 정보 설정 실패", e);
		} catch (UnsupportedEncodingException e) {
			throw new EmailException("이메일 보내는 사람 정보 설정 실패", e);
		}
		
	}
}