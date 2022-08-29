package com.codewithshaheen.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithshaheen.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionalHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionalHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse api = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.NOT_FOUND);
		
	}

}
