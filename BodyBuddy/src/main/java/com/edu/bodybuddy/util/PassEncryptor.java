package com.edu.bodybuddy.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.edu.bodybuddy.exception.EncryptFailException;


//평문을 해시값으로 변경
public class PassEncryptor {
	public static String encrypt(String pass) throws EncryptFailException{
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			//일반 스트링 쪼개기
			byte[] hash = digest.digest(pass.getBytes("UTF-8"));
			for (int i = 0; i < hash.length; i++) {
				//16진수로 변환 -> 최종적으로 16진수 문자열 반환
				String hex = Integer.toHexString(0xff & hash[i]);
				//반환된 16진수 문자열 누적시키기. 반환값이 1자리인 경우 2자리로 변환
				if(hex.length() == 1) sb.append(0);
				sb.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptFailException("비밀번호 암호화 실패", e);
		} catch (UnsupportedEncodingException e) {
			throw new EncryptFailException("비밀번호 암호화 실패", e);
		}
		return sb.toString();
	}
}
