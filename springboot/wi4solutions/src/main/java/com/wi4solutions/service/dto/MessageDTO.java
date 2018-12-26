package com.wi4solutions.service.dto;

public class MessageDTO {
	
	String code;
	
	String message;

	public MessageDTO() {
		super();
	}
	
	
	public MessageDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
