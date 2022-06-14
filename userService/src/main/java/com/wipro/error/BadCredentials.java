package com.wipro.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class BadCredentials extends RuntimeException {
	public BadCredentials(){
		super();
	}
	public BadCredentials(String message){
		super(message);
	}
}
