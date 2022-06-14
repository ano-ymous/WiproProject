package com.news.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FetchControllerAdvice {
	@ExceptionHandler(FetchingError.class)
	public ResponseEntity<String> fetchingErrorHandler(Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	@ExceptionHandler(CustomError.class)
	public ResponseEntity<String> customErrorHandler(Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
