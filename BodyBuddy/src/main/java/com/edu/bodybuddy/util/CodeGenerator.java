package com.edu.bodybuddy.util;

public class CodeGenerator {
	
	public static String generateCode() {
		double a = 0;
		while(a<0.1) {
			a = Math.random();
		}
		String code = (int)Math.ceil(a*1000000)+"";
		
		return code;
	}
}
