package com.news.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class FavoriteControllerAdvice {
	@ExceptionHandler(AlreadyFavorite.class)
	public ResponseEntity<ErrorResponse> handleAlreadyFavorite(Exception e){
		HttpStatus status = HttpStatus.CONFLICT;
		return ResponseEntity.status(status).body(new ErrorResponse(status,e.getMessage()));
	}
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound(Exception e){
		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status).body(new ErrorResponse(status,e.getMessage()));
		
	}
	@ExceptionHandler(CustomError.class)
	public ResponseEntity<ErrorResponse> handleCustomError(Exception e){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(new ErrorResponse(status,e.getMessage()));
		
	}
}
