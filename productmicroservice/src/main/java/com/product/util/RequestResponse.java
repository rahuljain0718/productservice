package com.product.util;

public class RequestResponse {

	private String message;
	private boolean success;
	
	public RequestResponse() {
		
	}
	
	public RequestResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
