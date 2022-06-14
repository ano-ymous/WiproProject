package com.wipro.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@ControllerAdvice
public class UserControllerAdvice {
	
	@ExceptionHandler(UserDoesNotExist.class)
	public ResponseEntity<ErrorResponse> handleUserDoesNotExist(Exception e){
		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(UserAlreadyExist.class)
	public ResponseEntity<ErrorResponse> handleUserAlreadyExist(Exception e){
		HttpStatus status = HttpStatus.CONFLICT;
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(CustomError.class)
	public ResponseEntity<ErrorResponse> handleCustomError(Exception e){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(BadCredentials.class)
	public ResponseEntity<ErrorResponse> handleBadCredentials(Exception e){
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNameNotFoundEception(Exception e){
		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(status,e.getMessage()));
	}
	
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
}
