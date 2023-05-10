package com.edu.bodybuddy.exception;

public class WeatherException extends RuntimeException{
	public WeatherException(String msg) {
		super(msg);
	}
	public WeatherException(String msg, Throwable e) {
		super(msg, e);
	}
}
