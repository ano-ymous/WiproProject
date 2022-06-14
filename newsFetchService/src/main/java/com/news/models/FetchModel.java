package com.news.models;

import java.util.List;

public class FetchModel {
	private String status;
	private Integer totalResults;
	private List<NewsModel> articles;
	FetchModel(){
		
	}
	public FetchModel(String status, Integer totalResults, List<NewsModel> articles) {
		super();
		this.status = status;
		this.totalResults = totalResults;
		this.articles = articles;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	public List<NewsModel> getArticles() {
		return articles;
	}
	public void setArticles(List<NewsModel> articles) {
		this.articles = articles;
	}
	
}
