package com.edu.bodybuddy.util;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.EmailException;

@Component
public class EmailManager {
	@Autowired
	private JavaMailSender javaMailSender;
	private Logger log = LoggerFactory.getLogger(getClass());
	//메일보내기
	public void send(Member member) throws EmailException{
		//이메일 정보를 담을 메시지 객체 생성
		log.info("메일샌더 있나? " + javaMailSender);
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
	
	public String sendAuthCode(Member member) throws EmailException{
		String code = generateCode();
		log.info("생성된 이메일 인증 코드 : "+code);
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		try {
			//받을 사람 정보 설정
			msg.addRecipient(RecipientType.TO, new InternetAddress(member.getEmail()));
			//보내는 사람 정보 설정
			msg.addFrom(new InternetAddress[] {
					new InternetAddress("winter0927@gmail.com","BodyBuddy")
			});
			
			//메일 제목
			msg.setSubject("이메일 인증 코드입니다");
			msg.setText("이메일 인증 코드 : "+code, "UTF-8");
			
			//메일 전송
			javaMailSender.send(msg);
		} catch (AddressException e) {
			throw new EmailException("이메일 주소가 존재하지 않습니다", e);
		} catch (MessagingException e) {
			throw new EmailException("이메일 발송 실패", e);
		} catch (UnsupportedEncodingException e) {
			throw new EmailException("이메일 인코딩 설정이 잘못되었습니다", e);
		}
		return code;
	}
	
	public String generateCode() {
		double a = 0;
		while(a<0.1) {
			a = Math.random();
		}
		String code = (int)Math.ceil(a*1000000)+"";
		
		return code;
	}
}