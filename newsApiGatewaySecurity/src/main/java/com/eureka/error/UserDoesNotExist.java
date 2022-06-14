package com.eureka.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UserDoesNotExist extends RuntimeException{
	public UserDoesNotExist(){
		super("User doesnot exist with given username");
	}
	public UserDoesNotExist(String message){
		super(message);
	}
}
