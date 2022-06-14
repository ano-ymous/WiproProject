package com.wipro.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class UserAlreadyExist extends RuntimeException {
	public UserAlreadyExist(){
		super();
	}
	public UserAlreadyExist(String message){
		super(message);
	}
}
