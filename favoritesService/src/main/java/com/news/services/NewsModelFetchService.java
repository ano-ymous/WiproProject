package com.news.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.errors.UserNotFound;
import com.news.models.FetchModel;
import com.news.models.NewsModel;
import com.news.repositories.FavoriteNewsRepository;

@Service
public class NewsModelFetchService {
	@Autowired
	private FavoriteNewsRepository newsFavRepo;
	
	public NewsModel fetchFavNewsByUrl(String username) 
			throws UserNotFound{
		return newsFavRepo.findById(username).orElseThrow(UserNotFound::new);
	}
	public NewsModel saveModel(NewsModel model) {
		return newsFavRepo.save(model);
	}
	public NewsModel insertModel(NewsModel news) {
		// TODO Auto-generated method stub
		return newsFavRepo.insert(news);
	}
	public void removeModel(NewsModel model) {
		newsFavRepo.delete(model);
	}
	
}
