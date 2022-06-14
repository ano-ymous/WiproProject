package com.news.models;

import java.util.Comparator;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.experimental.Accessors;

@Document(collection = "favoriteNews")
@Accessors(chain = true)
@Data
public class NewsModel{
	private SourceModel source;
	private String author;
	private String title;
	private String description;
	@Id
	private String url;
	private String urlToImage;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date publishedAt;
	@JsonIgnore
	private Integer favCount;
	private String content;
	public NewsModel(){
		
	}
	public NewsModel(SourceModel source, String author, String title, String description, String url, String urlToImage,
			Date publishedAt, Integer favCount, String content){
		super();
		this.source = source;
		this.author = author;
		this.title = title;
		this.description = description;
		this.url = url;
		this.urlToImage = urlToImage;
		this.publishedAt = publishedAt;
		this.favCount = favCount;
		this.content = content;
	}

	public SourceModel getSource() {
		return source;
	}
	public void setSource(SourceModel source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlToImage() {
		return urlToImage;
	}
	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}
	public Date getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFavCount() {
		return favCount;
	}
	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == this)
			return true;
		if(other.getClass() == this.getClass()) {
			NewsModel model = (NewsModel)other;
			return model.url.equalsIgnoreCase(this.url);
		}
		else
			return false;
	}
}
