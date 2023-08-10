package com.mycompany.propertymanagement.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExcpetionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException) {
		System.out.println("BusinessException is thrown");
		return new ResponseEntity<List<ErrorModel>>(businessException.getErrors(),HttpStatus.BAD_REQUEST);
	}
}
