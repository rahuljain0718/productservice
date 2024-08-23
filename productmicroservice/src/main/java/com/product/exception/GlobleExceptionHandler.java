package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.product.util.RequestResponse;
@ControllerAdvice
public class GlobleExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<String> handlerValidationException(ValidationException ex, WebRequest Webrequest) {

		return new ResponseEntity<String>(ex.getResponseMsg(), ex.getResponseStatus());

	}
	
}
