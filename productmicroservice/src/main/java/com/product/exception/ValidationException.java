package com.product.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends RuntimeException {
        
	private static final long serialVersionUID = 1L;
		private HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        private String responseMsg;
        
        private String resourceName;

    	private String message;
        public String getResourceName() {
			return resourceName;
		}

		public void setResourceName(String resourceName) {
			this.resourceName = resourceName;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public ValidationException(){
        	
        }
        
        public ValidationException(Exception exception){
        	super(exception.getMessage());
        }
        
        
        public ValidationException(String resourceName, String message) {
    		this.resourceName = resourceName;
    		this.message = message;
    	}
 
        public ValidationException(HttpStatus responseStatus,String responseMsg) {
        	this.responseStatus=responseStatus;
        	this.responseMsg=responseMsg;
        }

		public HttpStatus getResponseStatus() {
			return responseStatus;
		}

		public void setResponseStatus(HttpStatus responseStatus) {
			this.responseStatus = responseStatus;
		}

		public String getResponseMsg() {
			return responseMsg;
		}

		public void setResponseMsg(String responseMsg) {
			this.responseMsg = responseMsg;
		}
        		
}
