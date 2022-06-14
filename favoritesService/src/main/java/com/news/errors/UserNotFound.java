package com.news.errors;

public class UserNotFound extends RuntimeException {
	public UserNotFound(){
		super("No favorite list found with given username");
	}
	public UserNotFound(String message){
		super(message);
	}
}
