package com.aws.lg.recommendation.exception;

import java.util.Date;

public class ErrorMessage {
	
	private Date timestamp;
	private String message;
	
	public ErrorMessage() {
		super();
	}
	
	public ErrorMessage(String message) {
		super();
		this.message = message;
		this.timestamp=new Date();
	}


	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
