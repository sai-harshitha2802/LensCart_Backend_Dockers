package com.project1.exception;

import java.time.LocalDateTime;

public class ErrorCode {
	
	private String msg;
	private LocalDateTime timestamp;
	private Integer statusCode;
	public ErrorCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorCode(String msg, LocalDateTime timestamp, Integer statusCode) {
		super();
		this.msg = msg;
		this.timestamp = timestamp;
		this.statusCode = statusCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
