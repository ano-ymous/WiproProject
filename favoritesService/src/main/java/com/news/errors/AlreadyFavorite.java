package com.news.errors;

public class AlreadyFavorite extends RuntimeException {
	public AlreadyFavorite() {
		super("User has this news as favorite item");
	}
	public AlreadyFavorite(String message) {
		super(message);
	}
}
