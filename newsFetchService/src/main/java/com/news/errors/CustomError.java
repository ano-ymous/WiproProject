package com.news.errors;

public class CustomError extends Exception {
	public CustomError() {
		super("Encountered an errror");
	}
	public CustomError(String message) {
		super(message);
	}
}
