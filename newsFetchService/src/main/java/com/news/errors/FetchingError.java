package com.news.errors;


public class FetchingError extends Exception {
	public FetchingError(){
		super("Bad fetch try to give betters values");
	}
	public FetchingError(String message){
		super(message);
	}
}
