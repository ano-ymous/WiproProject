package com.news.models;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.experimental.Accessors;

@Document(collection = "")
@Accessors(chain = true)
@Data
public class FetchModel {
	@Id
	private String username;
	private Set<String> favoritesUrlList;
	@DBRef
	private List<NewsModel> articles;
	public FetchModel(){
		
	}

	public FetchModel(String username, Set<String> favoritesUrlList, List<NewsModel> articles) {
		super();
		this.username = username;
		this.favoritesUrlList = favoritesUrlList;
		this.articles = articles;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Set<String> getFavoritesUrlList() {
		return favoritesUrlList;
	}

	public void setFavoritesUrlList(Set<String> favoritesUrlList) {
		this.favoritesUrlList = favoritesUrlList;
	}

	public List<NewsModel> getArticles() {
		return articles;
	}
	public void setArticles(List<NewsModel> articles) {
		this.articles = articles;
	}
}
