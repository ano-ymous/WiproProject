package com.news.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.errors.AlreadyFavorite;
import com.news.errors.CustomError;
import com.news.errors.UserNotFound;
import com.news.models.FetchModel;
import com.news.models.NewsModel;
import com.news.services.FavoritesFetchService;
import com.news.services.NewsModelFetchService;
import com.news.services.ResponseService;

@RestController
@RequestMapping("news/api/favorites")
public class FavoritesController {
	
	@Autowired
	private FavoritesFetchService favFetchService;
	@Autowired
	private NewsModelFetchService favNewsService;
	
	@PostMapping("/add/{username}")
	public ResponseEntity<ResponseService> addFavorite(
			@PathVariable("username") String username,
			@RequestBody NewsModel news ) throws CustomError {
		FetchModel fetchedData = null;
		boolean alreadyRegistered = true;
		try {
			fetchedData = favFetchService.fetchModelByUsername(username);
		}
		catch(UserNotFound e) {
			fetchedData = new FetchModel();
			fetchedData.setUsername(username);
			fetchedData.setFavoritesUrlList(new HashSet<String>());
			fetchedData.setArticles(new ArrayList<NewsModel>());
			alreadyRegistered = false;
		}
		catch(Exception e) {
			throw new CustomError(e.getMessage());
		}
		System.out.println(alreadyRegistered);
		if(!alreadyRegistered || !favFetchService.fetchModelByUsername(username)
				.getFavoritesUrlList().contains(news.getUrl())) {
			try {
				if(news.getFavCount()==null) {
					news.setFavCount(1);
				}
				else {
					news.setFavCount(news.getFavCount()+1);
				}
				favNewsService.saveModel(news);
				fetchedData.getFavoritesUrlList().add(news.getUrl());
				fetchedData.getArticles().add(news);
				favFetchService.saveModel(fetchedData);
				return ResponseEntity.ok(new ResponseService("Favorite added"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new CustomError(e.getMessage());
			}
		}
		throw new AlreadyFavorite();
	}
	
	@GetMapping("/getlist/{username}")
	public Set<String> getFavoriteurlList(@PathVariable("username") String username) {
		return favFetchService.getUrlListByUsername(username);
	}
	
	@GetMapping("/getfavorites/{username}")
	public List<NewsModel> getFavorites(@PathVariable("username") String username){
		return favFetchService.getFavsListByUsername(username);
	}
	@PostMapping("/removefavorites/{username}")
	public void getFavorites(@PathVariable("username") String username,
			@RequestBody String favUrl) throws CustomError {
		FetchModel fetchedData = favFetchService.fetchModelByUsername(username);
		if(fetchedData==null)
			throw new CustomError("no favorites with given url exist for the user");
		NewsModel newsData = favNewsService.fetchFavNewsByUrl(favUrl);
		Set<String> list = fetchedData.getFavoritesUrlList();
		int i = 0;
		List<NewsModel> newslist = fetchedData.getArticles();
		while(i<newslist.size()) {
			if(newslist.get(i).equals(newsData)) {
				newslist.remove(i);
				fetchedData.setArticles(newslist);
				break;
			}
			i++;
		}
		list.remove(favUrl);
		fetchedData.setFavoritesUrlList(list);
		if(newsData.getFavCount()>1) {
			newsData.setFavCount(newsData.getFavCount()-1);
			favNewsService.saveModel(newsData);
		}
		else {
			favNewsService.removeModel(favNewsService.fetchFavNewsByUrl(favUrl));
		}
		favFetchService.saveModel(fetchedData);
	}
	
}
