package com.news.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.news.errors.UserNotFound;
import com.news.models.FetchModel;
import com.news.models.NewsModel;
import com.news.repositories.FavoritesFetchRepository;

@Service
public class FavoritesFetchService {
	@Autowired
	private FavoritesFetchRepository favFetchRepo;
	
	public FetchModel fetchModelByUsername(String username) 
			throws UserNotFound{
		return favFetchRepo.findById(username).orElseThrow(UserNotFound::new);
	}
	
	public FetchModel saveModel(FetchModel model) {
		return favFetchRepo.save(model);
	}
	
	public Set<String> getUrlListByUsername(String username) throws UserNotFound{
		Optional<FetchModel> list = favFetchRepo.findById(username);
		if(list.isEmpty())
			return new HashSet<String>();
		return list.get().getFavoritesUrlList();
	}
	
	public List<NewsModel> getFavsListByUsername(String username){
		Optional<FetchModel> list = favFetchRepo.findById(username);
		if(list.isEmpty())
			return new ArrayList<NewsModel>();
		return list.get().getArticles();
	}
	public void removeFav(String username,String url) {
		FetchModel model = this.fetchModelByUsername(username);
		model.getFavoritesUrlList().remove(url);
		this.saveModel(model);
	}
}
