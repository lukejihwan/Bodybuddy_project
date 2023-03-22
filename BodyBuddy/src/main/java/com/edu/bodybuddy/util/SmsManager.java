package com.edu.bodybuddy.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.edu.bodybuddy.domain.member.Member;
import com.edu.bodybuddy.exception.SMSException;
import com.edu.bodybuddy.social.NaverSMS;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SmsManager {
	@Autowired private NaverSMS naverSMS;
	private Logger log = LoggerFactory.getLogger(getClass()); 
	
	/*SMS 발송 메서드, session에 저정하기 위해 코드를 반환한다*/ 
	public String sendSMS(Member member) {
		
		//인증 확인용 난수를 만든다
		String code = CodeGenerator.generateCode();
		
		JsonObject body = new JsonObject();
		JsonObject toJson = new JsonObject();
	    JsonArray toArr = new JsonArray(); //Array를 만드는 이유 : api에서 value로 배열을 보내라고 한다...
	    
	    //body 구성
	    toJson.addProperty("to",member.getPhone());
	    toArr.add(toJson);
	    
	    body.addProperty("type","SMS");
	    body.addProperty("from", naverSMS.getAdminPhone());		
	    body.addProperty("content","[BodyBuddy] 인증번호는 ["+code+"] 입니다.");	
	    body.add("messages", toArr);		
	    
	    //header 구성
	    HttpHeaders headers=new HttpHeaders();
		String timestamp = System.currentTimeMillis()+"";
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add("x-ncp-apigw-timestamp", timestamp);
		headers.add("x-ncp-iam-access-key", naverSMS.getAccessKey());
		headers.add("x-ncp-apigw-signature-v2", makeSignature(timestamp)); //요청의 시간과 시그니처의 시간을 일치시켜야한다
		
		//헤더와 바디를 조립해서 요청한다
		HttpEntity httpEntity=new HttpEntity(body.toString(), headers); 
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange("https://sens.apigw.ntruss.com/sms/v2/services/ncp:sms:kr:303729120853:bodybuddy/messages",HttpMethod.POST, httpEntity, String.class);
		
		log.info("SMS 발송요청 결과 : "+responseEntity);
		
		return code;
	}

	
	public String makeSignature(String timestamp) throws SMSException{
		String space = " ";					// one space
		String newLine = "\n";					// new line
		String method = "POST";					// method
		String url = naverSMS.getSignatureUrl();	// url (include query string)
		String accessKey = naverSMS.getAccessKey();			// access key id (from portal or Sub Account)
		String secretKey = naverSMS.getSecretKey();
		
		log.info("SMS 엑세스키 : "+naverSMS.getAccessKey());
		log.info("SMS 시크릿값 : "+naverSMS.getSecretKey());
		
		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();
		
		log.info("빌드된 메시지는 : "+message);
		
		SecretKeySpec signingKey = null;
		String encodeBase64String = null;
		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.encodeBase64String(rawHmac);
		} catch (UnsupportedEncodingException e) {
			throw new SMSException("문자 발송 실패, 인코딩 오류입니다", e);
		} catch (NoSuchAlgorithmException e) {
			throw new SMSException("문자 발송 실패, 알고리즘 오류입니다", e);
		} catch (InvalidKeyException e) {
			throw new SMSException("문자 발송 실패, 어드민 키 오류입니다", e);
		}
		log.info("인코딩된 스트링 : "+encodeBase64String);
	  return encodeBase64String;
	}
}
