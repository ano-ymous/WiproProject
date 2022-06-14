package com.eureka.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class GatewayControllerAdvice {
	@ExceptionHandler(UnsupportedJwtException.class)
	public ResponseEntity<ErrorResponse> handleUnsupportedJwtException(Exception e){
		HttpStatus status = HttpStatus.FORBIDDEN;
		return ResponseEntity.status(status).body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ErrorResponse> handleMalformedJwtException(Exception e){
		HttpStatus status = HttpStatus.FORBIDDEN;
		return ResponseEntity.status(status).body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ErrorResponse> handleSignatureException(Exception e){
		HttpStatus status = HttpStatus.FORBIDDEN;
		return ResponseEntity.status(status)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ErrorResponse> handleExpiredJwtException(Exception e){
		HttpStatus status = HttpStatus.FORBIDDEN;
		return ResponseEntity.status(status)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> handleNullPointerException(Exception e){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(JwtTokenMissingException.class)
	public ResponseEntity<ErrorResponse> handleJwtTokenMissingException(Exception e){
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(JwtTokenMalformedException.class)
	public ResponseEntity<ErrorResponse> handleJwtTokenMalformedException(Exception e){
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(UserDoesNotExist.class)
	public ResponseEntity<ErrorResponse> handleUserDoesNotExist(Exception e){
		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status)
				.body(new ErrorResponse(status,e.getMessage()));
	}
}
